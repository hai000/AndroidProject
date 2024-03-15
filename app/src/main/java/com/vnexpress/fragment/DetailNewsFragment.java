package com.vnexpress.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.vnexpress.API.APIExpress;
import com.vnexpress.API.News;
import com.vnexpress.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailNewsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    WebView webView;
    public static final String TAG = DetailNewsFragment.class.getName();
    Button btnBack;
    View view;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    News news;
    private String mParam2;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_news, container, false);
        Bundle bundle = getArguments();
        news = (News) bundle.get("news");


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
                        "            images[i].style.maxWidth = '"+px+"px';\n" +
                        "            images[i].style.height = '"+px+"px';\n" +
                        "        };";
                webView.evaluateJavascript(script, null);
            }
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return true; // Vô hiệu hóa mở các liên kết
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getFragmentManager()!=null) {
                    getFragmentManager().popBackStack();
                    getFragmentManager().popBackStack();

                }
            }
        });
        new LoadFullNewsAsyncTask().execute();


        // Inflate the layout for this fragment
        return view;
    }

    public class LoadFullNewsAsyncTask extends AsyncTask<String, Void, News> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected News doInBackground(String...strings) {

            return new APIExpress().getFullNews(news);
        }

        @Override
        protected void onPostExecute(News news) {
            super.onPostExecute(news);
            webView.loadData(news.getDescription()==""?"<p>Trang báo đang lỗi<p>":news.getDescription(), "text/html", "UTF-8");
        }

    }



}