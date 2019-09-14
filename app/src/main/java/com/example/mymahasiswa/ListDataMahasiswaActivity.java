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
import android.widget.Toolbar;

import com.example.mymahasiswa.Model.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class ListDataMahasiswaActivity extends AppCompatActivity {

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


        MahasiswaDatabaseHelper db = new MahasiswaDatabaseHelper(getApplicationContext());
        mahasiswaList = db.selectMahasiswaData();
        RecyclerviewAdapter rvAdapter = new RecyclerviewAdapter(getApplicationContext(),mahasiswaList);
        recyclerView.setAdapter(rvAdapter);
        rvAdapter.notifyDataSetChanged();
    }
    public void rowmahasiswa(View view){
        OpenDialog();
    }
    public void OpenDialog(){
        DialogActivity Dialog = new DialogActivity();
        if (Dialog.getDialog() != null && Dialog.getDialog().getWindow() !=null){
            Dialog.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Dialog.getDialog().getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        }
        Dialog.show(getSupportFragmentManager(), "DIALOG");
    }





}
