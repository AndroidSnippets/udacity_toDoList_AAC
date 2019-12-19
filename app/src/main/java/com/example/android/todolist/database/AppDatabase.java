package com.example.android.todolist.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.android.todolist.database.DateConverter;
import com.example.android.todolist.database.TaskDAO;
import com.example.android.todolist.database.TaskEntry;

@Database(entities = {TaskEntry.class},version = 1,exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static final String TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "toDoList";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context){
        if(sInstance == null) {
            synchronized (LOCK){
                Log.d(TAG, "getInstance: Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            AppDatabase.DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
            }

        }
        Log.d(TAG, "getInstance: getting the database instance");
        return sInstance;
    }

    public abstract TaskDAO getTaskDAO();
}
