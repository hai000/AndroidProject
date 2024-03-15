package com.vnexpress;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import com.vnexpress.adapter.MenuItemAdapter;
import com.vnexpress.models.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {
    View view;
    HomeActivity homeActivity;

    RecyclerView rcvSetting;
    private MenuItemAdapter menuItemAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        homeActivity = (HomeActivity) getActivity();
        rcvSetting = view.findViewById(R.id.rcvSetting);
        menuItemAdapter = new MenuItemAdapter(homeActivity,getItemSetting());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(homeActivity,RecyclerView.VERTICAL,false);
        rcvSetting.setLayoutManager(linearLayoutManager);
        rcvSetting.setAdapter(menuItemAdapter);







        // Inflate the layout for this fragment
        return view;

    }
    public List<MenuItem> getItemSetting(){
        List<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(MenuItem.builder().title("Đăng nhập / Tạo tài khoản").icon(R.drawable.icon_account).isBold(true).build());
        menuItems.add(MenuItem.builder().title("Thiết lập ứng dụng").icon(R.drawable.icon_setting).build());
        menuItems.add(MenuItem.builder().title("Sắp xếp, ẩn, hiện chuyên mục").icon(R.drawable.icon_list).build());
        menuItems.add(MenuItem.builder().title("Xem sau").icon(R.drawable.icon_history).build());
        menuItems.add(MenuItem.builder().title("Tiện ích").icon(R.drawable.icon_app).build());
        menuItems.add(MenuItem.builder().title("Tin theo khu vực").hideIcon(true).titleGray(true).hideIconRight(true).build());
        menuItems.add(MenuItem.builder().title("Hà Nội").hideIcon(true).isBold(true).titleMargin(true).build());
        menuItems.add(MenuItem.builder().title("TP Hồ Chí Minh").hideIcon(true).isBold(true).titleMargin(true).build());
        menuItems.add(MenuItem.builder().title("Chuyên mục").hideIcon(true).titleGray(true).hideIconRight(true).build());
        menuItems.add(MenuItem.builder().title("Trang chủ").hideIcon(true).isBold(true).titleMargin(true).build());
        menuItems.add(MenuItem.builder().title("Mới nhất").hideIcon(true).isBold(true).titleMargin(true).build());
        menuItems.add(MenuItem.builder().title("Podcast").hideIcon(true).isBold(true).titleMargin(true).build());
        menuItems.add(MenuItem.builder().title("Thời sự").hideIcon(true).isBold(true).titleMargin(true).build());
        menuItems.add(MenuItem.builder().title("Góc nhìn").hideIcon(true).isBold(true).titleMargin(true).build());
        menuItems.add(MenuItem.builder().title("Thế giới").hideIcon(true).isBold(true).titleMargin(true).build());
        menuItems.add(MenuItem.builder().title("Video").hideIcon(true).isBold(true).titleMargin(true).build());
        menuItems.add(MenuItem.builder().title("Kinh doanh").hideIcon(true).isBold(true).titleMargin(true).build());
        menuItems.add(MenuItem.builder().title("Bất động sản").hideIcon(true).isBold(true).titleMargin(true).build());
        menuItems.add(MenuItem.builder().title("Khoa học").hideIcon(true).isBold(true).titleMargin(true).build());
        menuItems.add(MenuItem.builder().title("Giải trí").hideIcon(true).isBold(true).titleMargin(true).build());
        menuItems.add(MenuItem.builder().title("Thể thao").hideIcon(true).isBold(true).titleMargin(true).build());
        menuItems.add(MenuItem.builder().title("Pháp luật").hideIcon(true).isBold(true).titleMargin(true).build());
        menuItems.add(MenuItem.builder().title("Giáo dục").hideIcon(true).isBold(true).titleMargin(true).build());
        menuItems.add(MenuItem.builder().title("Sức khỏe").hideIcon(true).isBold(true).titleMargin(true).build());
        return menuItems;
    }
}