package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "samochody")
public class Samochody {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String marka;
    private String model;
    @ColumnInfo(name = "rok")
    private int rokProdukcji;
    private String kategoria;
    private boolean uszkodzony;

    public Samochody(int rokProdukcji, String model, String marka, String kategoria, boolean uszkodzony) {
        this.rokProdukcji = rokProdukcji;
        this.model = model;
        this.marka = marka;
        this.kategoria = kategoria;
        this.uszkodzony = uszkodzony;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRokProdukcji() {
        return rokProdukcji;
    }

    public void setRokProdukcji(int rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public boolean isUszkodzony() {
        return uszkodzony;
    }

    public void setUszkodzony(boolean uszkodzony) {
        this.uszkodzony = uszkodzony;
    }

    @Override
    public String toString() {
        return "Samochody{" +
                "marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", rokProdukcji=" + rokProdukcji +
                ", kategoria='" + kategoria + '\'' +
                ", uszkodzony=" + uszkodzony +
                '}';
    }
}
