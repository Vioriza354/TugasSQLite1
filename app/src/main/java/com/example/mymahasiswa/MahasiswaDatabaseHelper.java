package com.example.mymahasiswa;

import android.app.Person;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mymahasiswa.Model.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaDatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DB_NAME="db_mahasiswa";
    private static final String TABLE_NAME="tbl_mahasiswa";
    private static final String KEY_NUM = "nomor";
    private static final String KEY_NAME="nama";
    private static final String KEY_BIRTH="tanggal_lahir";
    private static final String KEY_SEX = "jenis_kelamin";
    private static final String KEY_ALAMAT = "alamat";

    public MahasiswaDatabaseHelper(Context context){ super(context,DB_NAME,null,DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableMahasiswa = "Create table "+TABLE_NAME+"("+KEY_NUM+" INTEGER PRIMARY KEY,"+KEY_NAME+" TEXT,"
                +KEY_BIRTH+ " TEXT,"+KEY_SEX+" TEXT,"+KEY_ALAMAT+" TEXT)";
        db.execSQL(createTableMahasiswa);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql =("Drop table if exists " + TABLE_NAME);
        db.execSQL(sql);
        onCreate(db);
    }
    public void insertData(Mahasiswa mahasiswa){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(KEY_NUM,mahasiswa.getNomor());
        v.put(KEY_NAME,mahasiswa.getNama());
        v.put(KEY_BIRTH,mahasiswa.getTanggal());
        v.put(KEY_SEX,mahasiswa.getJenis_kelamin());
        v.put(KEY_SEX,mahasiswa.getAlamat());
        db.insert(TABLE_NAME,null,v);
    }
    public List<Mahasiswa> selectMahasiswaData(){
        ArrayList<Mahasiswa> mList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String columns[] = {KEY_NUM,KEY_NAME,KEY_BIRTH,KEY_SEX,KEY_ALAMAT};
        Cursor c = db.query(TABLE_NAME,columns,null,null,null,null,null);

        while (c.moveToNext()){
            int num = c.getInt(0);
            String nama = c.getString(1);
            String tglLahir = c.getString(2);
            String jenkel = c.getString(3);
            String alamat = c.getString(4);

            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setNomor(num);
            mahasiswa.setNama(nama);
            mahasiswa.setTanggal(tglLahir);
            mahasiswa.setJenis_kelamin(jenkel);
            mahasiswa.setAlamat(alamat);
            mList.add(mahasiswa);
        }
        return mList;
    }
    public Mahasiswa selectDetailMahasiswa(int num){
        Mahasiswa mahasiswa = new Mahasiswa();
        SQLiteDatabase db = getReadableDatabase();
        String columns[] = {KEY_NUM,KEY_NAME,KEY_BIRTH,KEY_SEX,KEY_ALAMAT};
        Cursor c = db.rawQuery("Select * from tbl_mahasiswa where nomor ='"+num+"'",null);
        while(c.moveToLast()){
            mahasiswa.setNomor(c.getInt(0));
            mahasiswa.setNama(c.getString(1));
            mahasiswa.setTanggal(c.getString(2));
            mahasiswa.setJenis_kelamin(c.getString(3));
            mahasiswa.setAlamat(c.getString(4));
        }
    return mahasiswa;
    }

    public void update(Mahasiswa mahasiswa){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues v = new ContentValues();

        v.put(KEY_NAME,mahasiswa.getNama());
        v.put(KEY_BIRTH,mahasiswa.getTanggal());
        v.put(KEY_SEX,mahasiswa.getJenis_kelamin());
        v.put(KEY_ALAMAT,mahasiswa.getAlamat());

        String where = KEY_NUM+"='"+mahasiswa.getNomor()+"'";
        db.update(TABLE_NAME,v,where,null);
    }
    public void delete(int num){
        SQLiteDatabase db = getReadableDatabase();
        String where = KEY_NUM+"='"+num+"'";
        db.delete(TABLE_NAME,where,null);
    }
}
