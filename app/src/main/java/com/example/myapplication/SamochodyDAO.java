package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SamochodyDAO {
    @Insert
    public void wstawDoBazySamochod(Samochody samochody);
    @Insert
    public void wstawListeSamochodowDoBazy(Samochody ... samochody);
    @Delete
    public void usunZBazySamochod(Samochody samochody);
    @Update
    public void aktualizujSamochodWBazie(Samochody samochody);
    @Query("SELECT * FROM samochody")
    public List<Samochody> zwrocWszytskieSamochodyZBazy();
    @Query("SELECT marka FROM samochody WHERE rok >:rokParametr")
    public List<String> zwrocMarkiSamochodowODanymRoku(int rokParametr);
}
