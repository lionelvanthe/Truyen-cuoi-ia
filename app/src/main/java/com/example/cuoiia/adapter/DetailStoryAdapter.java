package com.example.cuoiia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.cuoiia.R;
import com.example.cuoiia.model.Story;

import java.util.List;

public class DetailStoryAdapter extends PagerAdapter {

    private List<Story> list;
    private Context context;

    public DetailStoryAdapter(List<Story> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_story, container, false);
        TextView name = view.findViewById(R.id.tv_item_story_name);
        TextView content = view.findViewById(R.id.tv_item_story_content);
        name.setText(list.get(position).getName().trim());
        content.setText(list.get(position).getContent().trim());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
