package com.application.blogger.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.application.blogger.logic.Post;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class Database extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "blogger_app";
    private static final int VERSION = 2;
    public static Dao<Post, Long> dao;

    public Database(Context context) {
        super(context, Database.DATABASE_NAME, null, Database.VERSION);
        getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Post.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Post.class, false);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Post, Long> getDao() throws SQLException {
        if (Database.dao == null) {
            try {
                Database.dao = getDao(Post.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}
