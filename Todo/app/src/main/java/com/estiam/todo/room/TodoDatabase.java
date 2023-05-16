package com.estiam.todo.room;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Todo.class}, version = 1)
public abstract class TodoDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "todo";

    public static TodoDatabase getDb(Context context){
        return Room.databaseBuilder(context, TodoDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
    }
    public abstract TodoDAO todoDao();

}
