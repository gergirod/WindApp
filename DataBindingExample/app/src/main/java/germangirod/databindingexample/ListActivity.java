package germangirod.databindingexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import germangirod.databindingexample.RecyclerAdapter.onRowClick;

/**
 * Created by germangirod on 3/14/16.
 */
public class ListActivity extends AppCompatActivity implements onRowClick {

    private RecyclerView recyclerView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        RecyclerAdapter adapter = new RecyclerAdapter(getApplicationContext(),createUsers());
        adapter.setRowClick(this);
        recyclerView.setAdapter(adapter);

    }

    public ArrayList<User> createUsers(){
        ArrayList<User> users = new ArrayList<>();
        for(int i=0; i< 10; i++){
            users.add(new User("rodrigo ","rueda","25"));
        }

        return users;
    }

    @Override public void clickWeatherRow(View v, int i) {
        Log.e("mirar aprete tal","mirar aprete tal "+i);
    }
}
