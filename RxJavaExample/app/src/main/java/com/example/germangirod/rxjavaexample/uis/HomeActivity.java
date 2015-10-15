package com.example.germangirod.rxjavaexample.uis;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.germangirod.rxjavaexample.R;
import com.example.germangirod.rxjavaexample.uis.adapters.HomePagerAdapter;

/**
 * Created by germangirod on 5/13/15.
 */
public class HomeActivity extends BaseActivity {

    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.tab_layout) TabLayout tabLayout;
    @InjectView(R.id.pager) ViewPager viewPager;

    private HomePagerAdapter homePagerAdapter;
    private String[] tableName = {"Current Location Weather","My Locations Weather"};

    @Override public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.inject(this);
        setToolbar();
        setTableLayoutTabs();
        setViewPager();
        setTabLayoutOnSelect();

    }

    private void setTableLayoutTabs(){

        for(int i=0; i<tableName.length; i++){

            tabLayout.addTab(tabLayout.newTab().setText(tableName[i]));

        }

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private void setViewPager(){
        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(homePagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void setTabLayoutOnSelect(){
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
