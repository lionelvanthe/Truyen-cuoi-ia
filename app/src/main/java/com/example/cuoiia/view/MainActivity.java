package com.example.cuoiia.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import com.example.cuoiia.OnActionCallBack;
import com.example.cuoiia.R;
import com.example.cuoiia.view.fragment.DetailFragment;
import com.example.cuoiia.view.fragment.SplashFragment;
import com.example.cuoiia.view.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity implements OnActionCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        SplashFragment splashFragment = new SplashFragment();
        splashFragment.setCallBack(this);
        showFragment(splashFragment, false);
    }

    private void showFragment(Fragment fragment, boolean addToBackStack){

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_start, R.anim.anim_end);
        transaction.replace(R.id.container_view,fragment);
        if(addToBackStack){
            transaction.addToBackStack("add");
        }
        transaction.commit();
    }

    @Override
    public void onCallBack(String key) {
        switch (key){
            case SplashFragment.KEY_SHOW_HOME:
                HomeFragment homeFragment = new HomeFragment();
                homeFragment.setCallBack(this);
                showFragment(homeFragment, false);
                break;
            case HomeFragment.KEY_SHOW_DETAIL:
                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setCallBack(this);
                showFragment(detailFragment, true);
                break;
        }
    }


}