package com.vnexpress;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.vnexpress.API.APIExpress;
import com.vnexpress.adapter.ViewPaggerAdapter;
import com.vnexpress.fragment.CategoryFragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    View view;



    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onDestroy() {
        view =null;
        super.onDestroy();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
     ViewPaggerAdapter viewPaggerAdapter;


    public HomeFragment() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view==null){
         view = inflater.inflate(R.layout.fragment_home, container, false);
        tabLayout = view.findViewById(R.id.tableLayout);
        viewPager = view.findViewById(R.id.viewPager);
        viewPaggerAdapter = viewPaggerAdapter==null?new ViewPaggerAdapter(getFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT):viewPaggerAdapter;
        viewPager.setAdapter(viewPaggerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);
        }
        return view;
    }
}