package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Samochody.class}, version = 2)
public abstract class SamochodyDataBase extends RoomDatabase {
    public abstract SamochodyDAO zwrocSamochodyDAO();
}
