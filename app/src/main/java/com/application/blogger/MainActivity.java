package com.application.blogger;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.application.blogger.database.Database;
import com.application.blogger.logic.Post;
import com.application.blogger.logic.PostAdapter;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview_post);
        try {
            Database database = new Database(this);
            List<Post> posts = database.getDao().queryForAll();
            PostAdapter arrayAdapter = new PostAdapter(this, R.layout.post_item_list_layout, posts);
            listView.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Post post = (Post) arrayAdapter.getItem(position);
                    Intent intent = new Intent(MainActivity.this, EditPostActivity.class);
                    intent.putExtra("post", post);
                    startActivity(intent);

                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_post:
                startActivity(new Intent(this, CreatePostActivity.class));
                return true;
            case R.id.about_us:
                startActivity(new Intent(this, AboutUsActivity.class));
                return true;
            case R.id.exit:
                finishAffinity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

