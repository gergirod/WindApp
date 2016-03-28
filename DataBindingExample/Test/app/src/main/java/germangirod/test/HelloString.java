package germangirod.test;

import android.app.Activity;

/**
 * Created by germangirod on 3/16/16.
 */
public class HelloString {


    public HelloString(){

    }

    public String getAppName(Activity context){
        return context.getResources().getString(R.string.app_name);
    }

}
