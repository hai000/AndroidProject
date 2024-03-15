package com.vnexpress.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vnexpress.API.APIExpress;
import com.vnexpress.API.Category;
import com.vnexpress.API.News;
import com.vnexpress.HomeActivity;
import com.vnexpress.R;
import com.vnexpress.adapter.NewsItemAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {
    RecyclerView rcvNews;
    NewsItemAdapter newsItemAdapter;
    List<News> newsList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CategoryFragment categoryFragment;

    View view;
    ProgressBar progressBar;
    String category="";



    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("AAA","onDestroy"+category);
    }

    public CategoryFragment(String category) {
        categoryFragment = this;
        this.category = category;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment(Category.tin_moi);
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
        view = inflater.inflate(R.layout.fragment_category, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        rcvNews = view.findViewById(R.id.rcvNews);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        newsItemAdapter = new NewsItemAdapter(getContext(), new NewsItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(News news) {

                    ((HomeActivity)getActivity()).gotoDetailNewsFragment(news);



            }
        });
        rcvNews.setLayoutManager(linearLayoutManager);
        rcvNews.setAdapter(newsItemAdapter);
        new AsyncTaskNetwork().execute();


//
//        APIExpress apiExpress = new APIExpress();
//        News news = apiExpress.getFullNews(apiExpress.getNews24h().get(0));
//        WebView webView = view.findViewById(R.id.textView);
//        float scale = getContext().getResources().getDisplayMetrics().density;
//        int size = (int) (90*scale + 0.5f);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebViewClient(new WebViewClient() {
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                // Kiểm tra xem URL được yêu cầu có phải là một liên kết không
//                // Nếu là một liên kết, trả về true để vô hiệu hóa mở liên kết
//                return true; // Vô hiệu hóa mở các liên kết
//            }
//            public void onPageFinished(WebView view, String url) {
//                String script =  "        var images = document.getElementsByTagName('img');" +
//                        "        for (var i = 0; i < images.length; i++) {" +
//                        "            images[i].style.maxWidth = '"+size+"';" +
//                        "            images[i].style.maxHeight = '"+size+"';" +
//                        "        }";
//                webView.evaluateJavascript(script, null);
//            }
//        });
//        webView.loadData(news.getDescription(), "text/html", "UTF-8");
        return view;
    }

    public class AsyncTaskNetwork extends AsyncTask<String, Void, List<News>> {

        public AsyncTaskNetwork() {

        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<News> doInBackground(String... strings) {

            newsList = new APIExpress().getNews(category);
            return newsList;
        }

        @Override
        protected void onPostExecute(List<News> newsList) {
            super.onPostExecute(newsList);
            progressBar.setVisibility(View.GONE);
            newsItemAdapter.setData(newsList);
        }
    }
    public void reloadData(){
        new AsyncTaskNetwork().execute();
    }
}