package com.example.germangirod.rxjavaexample.uis;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.example.germangirod.rxjavaexample.BaseActivity;
import com.example.germangirod.rxjavaexample.R;
import com.example.germangirod.rxjavaexample.api.database.Database;

/**
 * Created by germangirod on 5/13/15.
 */
public class HomeActivity extends BaseActivity {

    private FragmentTransaction fragmentTransaction;

    @Override public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Database database= new Database(getApplicationContext());

        database.save(1);
        database.getThis();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_content, new HomeFragment());
        fragmentTransaction.commit();
    }
}
