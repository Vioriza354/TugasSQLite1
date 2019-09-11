package com.example.mymahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout lihatdata,inputdata,informasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        lihatdata = (LinearLayout)findViewById(R.id.linearLihatData);
        lihatdata.setOnClickListener(this);
        inputdata = (LinearLayout)findViewById(R.id.linearInputData);
        inputdata.setOnClickListener(this);
        informasi = (LinearLayout) findViewById(R.id.informasi);
        informasi.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linearLihatData:
                Intent in = new Intent(HomeActivity.this, ListDataMahasiswaActivity.class);
                startActivity(in);
                break;
            case R.id.linearInputData:
                Intent in2 = new Intent(HomeActivity.this,FormTambahActivity.class);
                startActivity(in2);
                break;
            case R.id.informasi:
                Toast.makeText(this.getApplicationContext(),"Belum dibikin hehe",Toast.LENGTH_SHORT);

        }
    }
}
