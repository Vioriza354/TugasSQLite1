package com.example.mymahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mymahasiswa.Model.Mahasiswa;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    EditText nomor,nama,tglLahir,alamat,tanggal;
    RadioGroup jenkel;

    String action;

    RadioButton laki,wanita;
    RelativeLayout simpan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tambah);

        nomor = (EditText)findViewById(R.id.txtNomor);
        nama = (EditText)findViewById(R.id.txtNama);
        jenkel = (RadioGroup)findViewById(R.id.jenkel);
        alamat = (EditText)findViewById(R.id.txtAlamat);
        laki = (RadioButton)findViewById(R.id.laki);
        wanita = (RadioButton)findViewById(R.id.wanita);
        tanggal = (EditText)findViewById(R.id.txtTanggal);
        tglLahir = (EditText) findViewById(R.id.txtTanggal);

        simpan = (RelativeLayout)findViewById(R.id.simpanData);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        initData();
    }
    public void initData(){
        Intent intent = getIntent();

        nomor.setText(intent.getStringExtra("id"));
        nomor.setEnabled(false);

        nama.setText(intent.getStringExtra("nama"));
        tanggal.setText(intent.getStringExtra("tanggal"));

        if (intent.getStringExtra("jenkel").equals("LAKI-LAKI")){
            laki.isChecked();
        }
        else {
            wanita.isChecked();
        }
        alamat.setText(intent.getStringExtra("alamat"));
    }
    public void updateData(){
        MahasiswaDatabaseHelper db = new MahasiswaDatabaseHelper(getApplicationContext());
        Mahasiswa mh = new Mahasiswa();

        if (nomor.getText().toString().equals("")||nama.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Masukkan Nomor Mahasiswa dengan Benar!",Toast.LENGTH_SHORT).show();
        }else if(laki.isChecked()){
            mh.setNomor(nomor.getText().toString());
            mh.setNama(nama.getText().toString());
            mh.setTanggal(tglLahir.getText().toString());
            mh.setJenis_kelamin(laki.getText().toString());
            mh.setAlamat(alamat.getText().toString());

            db.update(mh);
            Toast.makeText(getApplicationContext(), "Data Berhasil Diubah", Toast.LENGTH_LONG).show();


            Intent i = new Intent(UpdateActivity.this, ListDataMahasiswaActivity.class);
            startActivity(i);
        }else if(wanita.isChecked()){
            mh.setNomor(nomor.getText().toString());
            mh.setNama(nama.getText().toString());
            mh.setTanggal(tglLahir.getText().toString());
            mh.setJenis_kelamin(wanita.getText().toString());
            mh.setAlamat(alamat.getText().toString());

            db.update(mh);
            Toast.makeText(getApplicationContext(), "Data Berhasil Diubah", Toast.LENGTH_LONG).show();

            Intent i = new Intent(UpdateActivity.this, ListDataMahasiswaActivity.class);
            startActivity(i);
        }


    }
    public void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tglLahir.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
