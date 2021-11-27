package com.example.cuoiia.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cuoiia.OnClickItemAdapter;
import com.example.cuoiia.R;
import com.example.cuoiia.adapter.StoryAdapter;
import com.example.cuoiia.model.Topic;
import com.example.cuoiia.viewmodel.SharedViewModel;

public class HomeFragment extends BaseFragment<SharedViewModel> implements OnClickItemAdapter {

    private DrawerLayout drawerLayout;
    private RecyclerView recyclerViewStory;
    private ImageView menu;
    private StoryAdapter storyAdapter;
    private LinearLayout lnTopic;
    private TextView toolbarTitle;
    public static final String KEY_SHOW_DETAIL = "KEY_SHOW_DETAIL";


    @Override
    protected void initViews() {
        menu = findViewById(R.id.img_menu);
        drawerLayout = findViewById(R.id.drawer);
        lnTopic = findViewById(R.id.ln_topic);
        recyclerViewStory = findViewById(R.id.recycler_view_story);
        toolbarTitle = findViewById(R.id.tool_bar_title);
        mModel.getNameTopic().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                toolbarTitle.setText(s);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        lnTopic.removeAllViews();
        mModel.setTopics();
        setUpRecyclerView();
        setUpLayoutTopic();

    }

    private void setUpRecyclerView(){

        storyAdapter = new StoryAdapter(mModel.getListStory(), HomeFragment.this, mContext);
        recyclerViewStory.setAdapter(storyAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        recyclerViewStory.addItemDecoration(itemDecoration);

    }

    private void setUpLayoutTopic(){
        for (Topic item : mModel.getListTopic()) {
            View v = initTopicView(item);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mModel.setStories(item.getName());
                    mModel.setNameTopic(item.getName());
                    setUpRecyclerView();
                    drawerLayout.closeDrawers();
                }
            });
            lnTopic.addView(v);
        }
    }

    private View initTopicView(Topic item) {

        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.item_topic, null, false);
        ImageView ivIcon = view.findViewById(R.id.iv_icon_topic);
        TextView tvName = view.findViewById(R.id.tv_topic_name);
        ivIcon.setImageBitmap(item.getBitmap());
        tvName.setText(item.getName());
        return view;
    }


    @Override
    protected Class<SharedViewModel> getClassViewModel() {
        return SharedViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    public void setOnClick(int i) {
        mModel.setStoryPosition(i);
        gotoDetail();
    }

    private void gotoDetail(){
        callBack.onCallBack(KEY_SHOW_DETAIL);
    }
}
