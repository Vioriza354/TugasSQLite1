package com.example.mymahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mymahasiswa.Model.Mahasiswa;

public class FormTambahActivity extends AppCompatActivity {

    EditText nomor,nama,tglLahir,jenkel,alamat;
    RelativeLayout simpan;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tambah);

        nomor = (EditText)findViewById(R.id.txtNomor);
        nama = (EditText)findViewById(R.id.txtNama);
        tglLahir = (EditText)findViewById(R.id.txtTanggal);
        jenkel = (EditText)findViewById(R.id.txtJenkel);
        alamat = (EditText)findViewById(R.id.txtAlamat);

        simpan = (RelativeLayout)findViewById(R.id.simpanData);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

    }
    public void insertData(){
        MahasiswaDatabaseHelper db = new MahasiswaDatabaseHelper(getApplicationContext());
        Mahasiswa mh = new Mahasiswa();

        mh.setNomor(Integer.parseInt(nomor.getText().toString()));
        mh.setNama(nama.getText().toString());
        mh.setTanggal(tglLahir.getText().toString());
        mh.setJenis_kelamin(jenkel.getText().toString());
        mh.setAlamat(alamat.getText().toString());

        db.insertData(mh);
        Toast.makeText(getApplicationContext(),"Data Berhasil Dimasukkan",Toast.LENGTH_LONG).show();

        Intent i = new Intent(FormTambahActivity.this,ListDataMahasiswaActivity.class);
        startActivity(i);
    }
}
