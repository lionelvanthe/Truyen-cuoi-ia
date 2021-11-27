package com.example.cuoiia.view.fragment;

import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;
import com.example.cuoiia.R;
import com.example.cuoiia.adapter.DetailStoryAdapter;
import com.example.cuoiia.viewmodel.SharedViewModel;

public class DetailFragment extends BaseFragment<SharedViewModel>{

    private ViewPager viewPager;
    private TextView tvTopic;
    private TextView tvStt;

    @Override
    protected void initViews() {
        viewPager = findViewById(R.id.view_pager_story);
        tvTopic = findViewById(R.id.tv_topic);
        tvStt = findViewById(R.id.tv_stt);
        tvStt.setText(String.format("%s/%s", "1", mModel.getListStory().size()));
        setNameTopic();
        setUpViewPager();
    }

    private void setNameTopic(){
        mModel.getNameTopic().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvTopic.setText(s);
            }
        });
    }

    private void setUpViewPager(){
        DetailStoryAdapter adapter = new DetailStoryAdapter(mModel.getListStory(), getContext());
        viewPager.setAdapter(adapter);

        mModel.getStoryPosition().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                viewPager.setCurrentItem(integer);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvStt.setText(String.format("%s/%s", (position + 1), mModel.getListStory().size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected Class<SharedViewModel> getClassViewModel() {
        return SharedViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.detail_fragment;
    }


}
