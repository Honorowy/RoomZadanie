package com.example.myapplication;

import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SamochodyDataBase samochodyDataBase;
    ArrayAdapter<Samochody> arrayAdapter;
    List<Samochody> samochody;
    Button buttonDodaj, buttonAkt;
    EditText editText, editText2, editText3;
    ListView listView;
    Samochody klikSamochod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        samochodyDataBase = Room.databaseBuilder(
            getApplicationContext(),
                SamochodyDataBase.class,
                "samochody_db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        buttonDodaj = findViewById(R.id.buttonDodaj);
        buttonAkt = findViewById(R.id.buttonAkt);
        listView = findViewById(R.id.listView);
        editText = findViewById(R.id.editTextText);
        editText2 = findViewById(R.id.editTextText2);
        editText3 = findViewById(R.id.editTextNumber);
        samochody = samochodyDataBase.zwrocSamochodyDAO().zwrocWszytskieSamochodyZBazy();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, samochody);
        listView.setAdapter(arrayAdapter);
        buttonDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rok = Integer.parseInt(editText3.getText().toString());
                Samochody samochody = new Samochody(rok, editText2.getText().toString(), editText.getText().toString() , "sedan", false);
                samochodyDataBase.zwrocSamochodyDAO().wstawDoBazySamochod(samochody);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Samochody samochodyDoUsuniecia = samochody.get(i);
                samochodyDataBase.zwrocSamochodyDAO().usunZBazySamochod(samochodyDoUsuniecia);
                samochody.remove(i);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                klikSamochod = samochody.get(i);
                editText.setText(klikSamochod.getMarka());
                editText2.setText(klikSamochod.getModel());
                editText3.setText(String.valueOf(klikSamochod.getRokProdukcji()));
                buttonAkt.setVisibility(View.VISIBLE);
            }
        });
        buttonAkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                klikSamochod.setMarka(editText.getText().toString());
                klikSamochod.setModel(editText2.getText().toString());
                int rok = Integer.parseInt(editText3.getText().toString());
                klikSamochod.setRokProdukcji(rok);
                samochodyDataBase.zwrocSamochodyDAO().aktualizujSamochodWBazie(klikSamochod);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}