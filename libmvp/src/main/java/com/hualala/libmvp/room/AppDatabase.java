package com.hualala.libmvp.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(version = 3,entities = {User.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}