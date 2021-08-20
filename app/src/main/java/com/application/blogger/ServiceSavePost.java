package com.application.blogger;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.application.blogger.logic.Post;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class ServiceSavePost extends Service {
    public ServiceSavePost() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Post post = (Post) intent.getSerializableExtra("post");
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplication().openFileOutput(post.title, Context.MODE_PRIVATE));
            String data = String.format("Title: %s \n Author: %s \n Date: %s \n Message: %s", post.title,post.author,post.date,post.message);
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            System.out.println("Service is running");
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}