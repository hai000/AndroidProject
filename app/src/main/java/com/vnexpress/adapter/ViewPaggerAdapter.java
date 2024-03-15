package com.vnexpress.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.vnexpress.API.Category;
import com.vnexpress.fragment.CategoryFragment;


public class ViewPaggerAdapter extends FragmentStatePagerAdapter {


    public ViewPaggerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CategoryFragment("");
            case 1:
                return new CategoryFragment(Category.tin_moi);
            case 2:
                return new CategoryFragment(Category.podcast);
            case 3:
                return new CategoryFragment(Category.thoi_su);
            case 4:
                return new CategoryFragment(Category.goc_nhin);
            case 5:
                return new CategoryFragment(Category.the_gioi);
            case 6:
                return new CategoryFragment(Category.kinh_te);
            case 7:
                return new CategoryFragment(Category.giai_tri);
            case 8:
                return new CategoryFragment(Category.the_thao);
            case 9:
                return new CategoryFragment(Category.phap_luat);
            case 10:
                return new CategoryFragment(Category.giao_duc);
            case 11:
                return new CategoryFragment(Category.suc_khoe);
            case 12:
                return new CategoryFragment(Category.van_hoa);
            case 13:
                return new CategoryFragment(Category.du_lich);
            case 14:
                return new CategoryFragment(Category.xe);
            default:
                return new CategoryFragment("Trang chủ");

        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Trang chủ";
                break;
            case 1:
                title = "Tin mới";
                break;
            case 2:
                title = "Podcast";
                break;
            case 3:
                title = "Thời sự";
                break;
            case 4:
                title = "Góc nhìn";
                break;
                case 5:
                title = "Thế giới";
                break;
            case 6:
                title = "Kinh tế";
                break;
            case 7:
                title = "Giải trí";
                break;
            case 8:
                title = "Thể thao";
                break;
            case 9:
                title = "Pháp luật";
                break;
            case 10:
                title = "Giáo dục";
                break;
            case 11:
                title = "Sức khỏe";
                break;
            case 12:
                title = "Văn hóa";
                break;
            case 13:
                title = "Du lịch";
                break;
            case 14:
                title = "Xe";
                break;
        }
        return title;
    }

    @Override
    public int getCount() {
        return 14;
    }
}
