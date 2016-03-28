package germangirod.frescoexample;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by germangirod on 3/14/16.
 */
public class FrescoExample extends Application {

    @Override public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
