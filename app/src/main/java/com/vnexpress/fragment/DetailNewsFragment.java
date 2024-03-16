package com.vnexpress.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.browse.MediaBrowser;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.SimpleExoPlayer;
import androidx.media3.ui.PlayerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;
import com.vnexpress.API.APIExpress;
import com.vnexpress.API.Category;
import com.vnexpress.API.News;
import com.vnexpress.R;
import com.vnexpress.models.Obserable;
import com.vnexpress.models.ObserverNetworking;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import lombok.SneakyThrows;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailNewsFragment extends Fragment implements Obserable {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    WebView webView;
    public static final String TAG = DetailNewsFragment.class.getName();
    Button btnBack, btnPlay, btnPrev, btnNext;
    View view;
    TextView timeCurrnet, timeTotal;
    SeekBar seekBar;
    ImageView imgCover;
    CardView cardView;


    private Timer timer;
    ExoPlayer exoPlayer;
    Bitmap bitmap;
    RequestCreator requestCreator;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    News news;
    private String mParam2;
    MediaPlayer mediaPlayer;
    Activity activity;

    public DetailNewsFragment() {

        // Required empty public constructorư

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailNewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailNewsFragment newInstance(String param1, String param2) {
        DetailNewsFragment fragment = new DetailNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mediaPlayer = new MediaPlayer();
    }

    @SuppressLint({"UnsafeOptInUsageError", "NewApi"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_news, container, false);
        Bundle bundle = getArguments();
        news = (News) bundle.get("news");

        activity = getActivity();


        exoPlayer = new SimpleExoPlayer.Builder(getContext()).build();


        if (news.getCategory().equals(Category.podcast)) {

            requestCreator = Picasso.get().load(news.getImage());
            cardView = view.findViewById(R.id.nav_audio);

            Target target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap img, Picasso.LoadedFrom from) {
                    // Bitmap đã được tải thành công, bạn có thể sử dụng nó ở đây
                    bitmap = img;
                    int pixelColorFrist = bitmap.getPixel(10, 10);
                    int pixelColorEnd = bitmap.getPixel(bitmap.getWidth() - 10, bitmap.getHeight() - 10);


                    // Đổi các giá trị màu thành một màu ARGB
                    int argbColorFirst = Color.argb(Color.alpha(pixelColorFrist), Color.red(pixelColorFrist), Color.green(pixelColorFrist), Color.blue(pixelColorFrist));
                    int argbColorEnd = Color.argb(Color.alpha(pixelColorEnd), Color.red(pixelColorEnd), Color.green(pixelColorEnd), Color.blue(pixelColorEnd));


                    GradientDrawable gradientDrawable = new GradientDrawable(
                            GradientDrawable.Orientation.TL_BR,
                            new int[]{argbColorFirst, argbColorEnd});
                    gradientDrawable.setCornerRadius(20f);


                    cardView.setBackground(gradientDrawable);
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                    // Xử lý khi việc tải ảnh thất bại
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    // Xử lý khi ảnh đang được chuẩn bị tải
                }
            };

            cardView.setVisibility(View.VISIBLE);

            // Sử dụng Picasso để tải ảnh và nhận Bitmap
            requestCreator.into(target);
            imgCover = view.findViewById(R.id.imgPodcast);
            timeCurrnet = view.findViewById(R.id.tvCurrentTime);
            timeTotal = view.findViewById(R.id.tvTotalTime);
            seekBar = view.findViewById(R.id.seekBar);
            btnPlay = view.findViewById(R.id.btnPlay);
            btnPrev = view.findViewById(R.id.btnPrev5s);
            btnNext = view.findViewById(R.id.btnNext5s);
            requestCreator.into(imgCover);
            btnPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exoPlayer.seekTo(exoPlayer.getCurrentPosition() - 5000);
                }
            });
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exoPlayer.seekTo(exoPlayer.getCurrentPosition() + 5000);
                }
            });
            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (exoPlayer.isPlaying()) {
                        exoPlayer.pause();
                        btnPlay.setBackground(getResources().getDrawable(R.drawable.icon_resume));
                    } else {
                        if (exoPlayer.getPlaybackState() == ExoPlayer.STATE_ENDED) {
                            exoPlayer.seekTo(0);
                        }
                        exoPlayer.play();
                        btnPlay.setBackground(getResources().getDrawable(R.drawable.icon_pause));
                    }
                }
            });

        }

        btnBack = view.findViewById(R.id.btnBack);
        webView = view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        float density = getContext().getResources().getDisplayMetrics().density;
        float px = 90 * density;
        // Thiết lập WebViewClient để xử lý các sự kiện trong WebView
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                // Thực thi JavaScript sau khi trang đã được load
                String script = " var images = document.getElementsByTagName('img');\n" +
                        "        for (var i = 0; i < images.length; i++) {\n" +
                        "            images[i].style.maxWidth = '" + px + "px';\n" +
                        "            images[i].style.height = '" + px + "px';\n" +
                        "        };";
                webView.evaluateJavascript(script, null);
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return true; // Vô hiệu hóa mở các liên kết
            }
        });
        APIExpress.getInstance().addObserable(this);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();


                }
            }
        });

        new LoadFullNewsAsyncTask().execute();


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void update(News a) {


        try {

            MediaItem mediaItem = MediaItem.fromUri(a.getLinkMp3());
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {


                    // Access or manipulate ExoPlayer here
                    exoPlayer.setMediaItem(mediaItem);
                    exoPlayer.prepare();
                    exoPlayer.setPlayWhenReady(true);
                    exoPlayer.addListener(new ExoPlayer.Listener() {
                        @Override
                        public void onPlaybackStateChanged(int state) {
                            if (state == ExoPlayer.STATE_READY) {
                                long durationInSeconds = exoPlayer.getDuration(); // Độ dài của bài hát trong giây (ví dụ)
                                long minutes = durationInSeconds / 60000;
                                long seconds = (int) durationInSeconds % 60000 / 1000;
                                String durationFormatted = String.format("%02d:%02d", minutes, seconds);
                                timeTotal.setText(durationFormatted);
                                seekBar.setMax((int) exoPlayer.getDuration());
                                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                    @Override
                                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                        if (fromUser) {
                                            timeCurrnet.setText(String.format("%02d:%02d", progress / 60000, (progress % 60000) / 1000));
                                            exoPlayer.seekTo(progress);
                                        }
                                    }

                                    @Override
                                    public void onStartTrackingTouch(SeekBar seekBar) {

                                    }

                                    @Override
                                    public void onStopTrackingTouch(SeekBar seekBar) {

                                    }

                                });
                                TimerTask timerTask = new TimerTask() {
                                    @Override
                                    public void run() {
                                        // Lấy thời gian hiện tại của media hoặc tiến trình
                                        // Ở đây tôi sẽ sử dụng thời gian giả định là currentTimeMillis


                                        // Cập nhật giá trị của SeekBar trong UI Thread
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                timeCurrnet.setText(String.format("%02d:%02d", exoPlayer.getCurrentPosition() / 60000, (exoPlayer.getCurrentPosition() % 60000) / 1000));
                                                seekBar.setProgress((int) (exoPlayer.getCurrentPosition()));
                                            }
                                        });
                                    }
                                };

                                // Khởi tạo Timer và chạy TimerTask mỗi giây
                                timer = new Timer();
                                timer.schedule(timerTask, 0, 1000);
                            }
                            if (state == ExoPlayer.STATE_ENDED) {
                                btnPlay.setBackground(getResources().getDrawable(R.drawable.icon_resume));
                            }


                        }


                    });
                }
            });

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public class LoadFullNewsAsyncTask extends AsyncTask<String, Void, News> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @SneakyThrows
        @Override
        protected News doInBackground(String... strings) {

            return new APIExpress().getFullNews(news);
        }

        @Override
        protected void onPostExecute(News news) {
            super.onPostExecute(news);

            webView.loadData(news.getDescription() == "" ? "<p>Trang báo đang lỗi<p>" : news.getDescription(), "text/html", "UTF-8");
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        exoPlayer.release();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private Runnable myRunnable = new Runnable() {
        @SneakyThrows
        @Override
        public void run() {

            bitmap = requestCreator.get();
        }
    };
}