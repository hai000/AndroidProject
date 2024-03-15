package com.vnexpress.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vnexpress.R;
import com.vnexpress.models.MenuItem;

import java.util.List;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder> {
    List<MenuItem> menuItems;
    Context context;

    public MenuItemAdapter(Context context,List<MenuItem> menuItems) {
        this.context = context;
        this.menuItems = menuItems;
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MenuItemViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);
        if (menuItem == null) {
            return;
        }
        holder.iconRight.setImageResource(R.drawable.icon_arrow_right);


            holder.title.setVisibility(menuItem.isHideTitle() ? View.GONE : View.VISIBLE);
            holder.title.setText(menuItem.getTitle());
            holder.icon.setVisibility(menuItem.isHideIcon() ? View.GONE : View.VISIBLE);

            holder.icon.setImageResource(menuItem.getIcon());

            holder.title.setTypeface(null, menuItem.isBold()? Typeface.BOLD:Typeface.NORMAL);


            float scale = context.getResources().getDisplayMetrics().density;
            int size = (int) (10*scale + 0.5f);
            holder.title.setPadding(menuItem.isTitleMargin()? size:0, 0, 0, 0);
        holder.title.setTextColor(menuItem.isTitleGray()? context.getColor(R.color.gray):context.getColor(R.color.black));

            holder.iconRight.setVisibility(menuItem.isHideIconRight()?View.GONE:View.VISIBLE);


    }

    @Override
    public int getItemCount() {
        if (menuItems != null)
            return menuItems.size();
        return 0;
    }

    public void setData(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
        notifyDataSetChanged();
    }

public class MenuItemViewHolder extends RecyclerView.ViewHolder {
    ImageView icon;
    ImageView iconRight;
    TextView title;

    public MenuItemViewHolder(View itemView) {
        super(itemView);
        icon = itemView.findViewById(R.id.icon_item_menu);
        iconRight = itemView.findViewById(R.id.icon_item_menu_right);
        title = itemView.findViewById(R.id.title_item_menu);

    }
}
}
