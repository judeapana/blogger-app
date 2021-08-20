package com.application.blogger;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.application.blogger.database.Database;
import com.application.blogger.logic.Post;

import java.sql.SQLException;

public class CreatePostActivity extends AppCompatActivity {
    private EditText author, title, date, message;
    private MenuItem save;
    private Menu optionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Create New Post");
        setContentView(R.layout.activity_create_post);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_post, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public Post post() {
        author = (EditText) findViewById(R.id.txt_author);
        title = (EditText) findViewById(R.id.txt_title);
        date = (EditText) findViewById(R.id.txt_date);
        message = (EditText) findViewById(R.id.txt_msg);
        if (title.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Title is empty", Toast.LENGTH_SHORT).show();
            return null;
        } else if (author.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Author is empty", Toast.LENGTH_SHORT).show();
            return null;
        } else if (date.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Date is empty", Toast.LENGTH_SHORT).show();
            return null;
        } else if (message.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Message/Post is empty", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            return new Post(title.getText().toString(), date.getText().toString(), message.getText().toString(), author.getText().toString());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            if (post() != null) {
                try {
                    Database database = new Database(this);
                    if (database.getDao().create(post()) != 1) {
                        Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        throw new Exception("Failure adding account");
                    } else {
                        Toast.makeText(this, "Your Post has been created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, MainActivity.class));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}