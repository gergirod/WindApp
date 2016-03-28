package germangirod.databindingexample;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import germangirod.databindingexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private User user;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding =DataBindingUtil.setContentView(this, R.layout.activity_main);

        user = new User("german","girod","18");
        activityMainBinding.setUser(user);
    }

    public void changeData(View view){
        user.setName("Chimbulain");
        user.setSurname("Suarez");
        startActivity(new Intent(this, ListActivity.class));
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
