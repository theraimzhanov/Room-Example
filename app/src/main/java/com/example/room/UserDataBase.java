package com.example.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserInfo.class},version = 1,exportSchema = false)
public abstract  class UserDataBase extends RoomDatabase {
    private  static  UserDataBase dataBase;
    private static final String DATABASE_NAME = "database";


    public synchronized static UserDataBase getInstance(Context context){
        if (dataBase == null){
            dataBase = Room.databaseBuilder(context.getApplicationContext(), UserDataBase.class,DATABASE_NAME).
                    allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return dataBase;
    }
    public abstract UserDao userDao();
}
