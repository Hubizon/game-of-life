package com.example.hubert.gameoflife.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.example.hubert.gameoflife.db.converter.DateConverter;
import com.example.hubert.gameoflife.db.entity.UserEntity;

@Database(entities = {UserEntity.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;

}