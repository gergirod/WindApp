package com.example.germangirod.rxjavaexample.uis;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.germangirod.rxjavaexample.BaseActivity;
import com.example.germangirod.rxjavaexample.R;
import android.support.v7.widget.Toolbar;

/**
 * Created by germangirod on 5/13/15.
 */
public class HomeActivity extends BaseActivity {

    @InjectView(R.id.toolbar) Toolbar toolbar;

    private FragmentTransaction fragmentTransaction;

    @Override public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.inject(this);
        setToolbar();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_content, new HomeFragment());
        fragmentTransaction.commit();
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.abc_btn_radio_material);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
