package com.example.mymahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymahasiswa.Model.Mahasiswa;

public class DetailDataActivity extends AppCompatActivity {

    EditText etnomor,etnama,ettanggal,etjenkel,etalamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);
        etnomor = (EditText)findViewById(R.id.txtaNomor);
        etnama = (EditText)findViewById(R.id.txtaNama);
        ettanggal = (EditText)findViewById(R.id.txtaTanggal);
        etjenkel = (EditText)findViewById(R.id.txtaJenkel);
        etalamat = (EditText)findViewById(R.id.txtaAlamat);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nama = intent.getStringExtra("nama");
        String tanggal = intent.getStringExtra("tanggal");
        String jenkel = intent.getStringExtra("jenkel");
        String alamat = intent.getStringExtra("alamat");

        Toast.makeText(getApplicationContext(),id,Toast.LENGTH_LONG);


        etnomor.setText(id);
        etnama.setText(nama);
        ettanggal.setText(tanggal);
        etjenkel.setText(jenkel);
        etalamat.setText(alamat);


    }
}
