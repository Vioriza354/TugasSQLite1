package com.example.mymahasiswa;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymahasiswa.Model.Mahasiswa;

import java.util.ArrayList;
import java.util.List;


public class DialogActivity extends DialogFragment implements View.OnClickListener {


    List<Mahasiswa> mahasiswaList =new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_dialog, container, false);
        if(getDialog() != null && getDialog().getWindow() !=null){
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        }
        return view;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog, null);


        Button lihatdata = (Button)view.findViewById(R.id.lihatdata);
        lihatdata.setOnClickListener(this);
        Button update = (Button)view.findViewById(R.id.updatedata);
        update.setOnClickListener(this);
        Button hapus = (Button)view.findViewById(R.id.hapusdata);
        hapus.setOnClickListener(this);

        builder.setView(view);
        return builder.create();
    }
//    public void setupRecyclerView(){
//        MahasiswaDatabaseHelper db = new MahasiswaDatabaseHelper(getActivity());
//        mahasiswaList = db.selectMahasiswaData();
//        RecyclerviewAdapter rvAdapter = new RecyclerviewAdapter(getActivity(),mahasiswaList,this);
//        recyclerView.setAdapter(rvAdapter);
//        rvAdapter.notifyDataSetChanged();
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lihatdata:
                Intent intent = new Intent(getContext(),DetailDataActivity.class);
                Bundle bundle = getArguments();
                intent.putExtra("id",bundle.getString("nomor"));
                intent.putExtra("nama",bundle.getString("nama"));
                intent.putExtra("tanggal",bundle.getString("tanggal"));
                intent.putExtra("jenkel",bundle.getString("jenkel"));
                intent.putExtra("alamat",bundle.getString("alamat"));
                dismiss();
                startActivity(intent);
                break;
            case R.id.updatedata:
                Intent i = new Intent(getContext(),UpdateActivity.class);
                Bundle b = getArguments();
                i.putExtra("id",b.getString("nomor"));
                i.putExtra("nama",b.getString("nama"));
                i.putExtra("tanggal",b.getString("tanggal"));
                i.putExtra("jenkel",b.getString("jenkel"));
                i.putExtra("alamat",b.getString("alamat"));

                dismiss();
                startActivity(i);
                break;
            case R.id.hapusdata:
                MahasiswaDatabaseHelper mhd = new MahasiswaDatabaseHelper(getContext());
                b = getArguments();
                mhd.delete(b.getString("nomor"));
                Toast.makeText(getContext(),"Data Berhasil Dihapus",Toast.LENGTH_SHORT).show();
                dismiss();
                break;
        }
    }

}
