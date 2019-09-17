package com.example.mymahasiswa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.example.mymahasiswa.Model.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class ListDataMahasiswaActivity extends AppCompatActivity implements RecyclerviewAdapter.OnUserClickListener {

    List<Mahasiswa> mahasiswaList =new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerviewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_mahasiswa);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        RelativeLayout input = (RelativeLayout)findViewById(R.id.inputList);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListDataMahasiswaActivity.this,FormTambahActivity.class);
                startActivity(i);
            }
        });


        MahasiswaDatabaseHelper db = new MahasiswaDatabaseHelper(getApplicationContext());
        mahasiswaList = db.selectMahasiswaData();
        RecyclerviewAdapter rvAdapter = new RecyclerviewAdapter(getApplicationContext(),mahasiswaList,this);
        recyclerView.setAdapter(rvAdapter);
        rvAdapter.notifyDataSetChanged();
    }
    public void OpenDialog(String id,String nama,String tanggal,String jenkel,String alamat){
        DialogActivity Dialog = new DialogActivity();
        Bundle bundle = new Bundle();
        bundle.putString("nomor",id);
        bundle.putString("nama",nama);
        bundle.putString("tanggal",tanggal);
        bundle.putString("jenkel",jenkel);
        bundle.putString("alamat",alamat);
        if (Dialog.getDialog() != null && Dialog.getDialog().getWindow() !=null){
            Dialog.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Dialog.getDialog().getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        }
        Dialog.setArguments(bundle);
        Dialog.show(getSupportFragmentManager(), "DIALOG");
    }


    @Override
    public void onUserClick(String id,String nama,String tanggal,String jenkel,String alamat) {
        OpenDialog(id,nama,tanggal,jenkel,alamat);
    }
}
