package com.application.blogger;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.application.blogger.database.Database;
import com.application.blogger.logic.Post;

import java.sql.SQLException;

public class EditPostActivity extends AppCompatActivity {

    private Post post;
    private EditText author, title, date, message;
    private MenuItem save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Edit Post");
        setContentView(R.layout.activity_edit_post);
        Intent intent = getIntent();
        post = (Post) intent.getSerializableExtra("post");
        author = findViewById(R.id.txt_author);
        author.setText(post.getAuthor());
        title = findViewById(R.id.txt_title);
        title.setText(post.getTitle());
        date = findViewById(R.id.txt_date);
        date.setText(post.getDate());
        message = findViewById(R.id.txt_msg);
        message.setText(post.getMessage());
    }

    public Post edit() {
        author = findViewById(R.id.txt_author);
        title = findViewById(R.id.txt_title);
        date = findViewById(R.id.txt_date);
        message = findViewById(R.id.txt_msg);
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
            post.setTitle(title.getText().toString());
            post.setDate(date.getText().toString());
            post.setMessage(message.getText().toString());
            post.setAuthor(author.getText().toString());
            return post;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meu_edit_post, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.edit) {
            if (edit() != null) {
                try {
                    Database database = new Database(this);
                    database.getDao().createOrUpdate(post);
                    Toast.makeText(this, "Your Post has been updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                } catch (SQLException throwables) {
                    Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    throwables.printStackTrace();
                } catch (Exception e) {
                    Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }
            }
        } else if (item.getItemId() == R.id.delete) {
            try {
                Database database = new Database(this);
                database.getDao().delete(post);
                Toast.makeText(this, "Your Post has been deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
            } catch (SQLException e) {
                Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (Exception e) {
                Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                e.printStackTrace();

            }
        } else if (item.getItemId() == R.id.export) {
            Intent intent = new Intent(this, ServiceSavePost.class);
            intent.putExtra("post", post);
            this.startService(intent);
        }
        return true;
    }
}