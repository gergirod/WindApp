package com.example.germangirod.rxjavaexample.uis;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by germangirod on 5/15/15.
 */
public abstract class BaseActivity extends ActionBarActivity {

    public abstract int getLayoutId();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }
}
