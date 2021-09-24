package com.example.trab1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DAO extends SQLiteOpenHelper {

    public DAO(@Nullable Context context) {
        super(context, "apkDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE FORM_EMPLOYMENT (ID INTEGER PRIMARY KEY AUTOINCREMENT, FORM_NAME TEXT, FORM_NUMBER TEXT, FORM_EMPLOYED BOOL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldV, int newV) {

    }

    public void addOne(FormModel model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        
        cv.put("FORM_NAME", model.getName());
        cv.put("FORM_NUMBER", model.getNumber());
        cv.put("FORM_EMPLOYED", model.isEmployed());

        db.insert("FORM_EMPLOYMENT", null, cv);
        System.out.println(cv);
    }

    public List<FormModel> getAll(){
        List<FormModel> entries = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "SELECT * FROM FORM_EMPLOYMENT",null);
        if (cursor.moveToFirst()) {
            do {
                FormModel entry = new FormModel();
                entry.setId(cursor.getInt(0));
                entry.setName(cursor.getString(1));
                entry.setNumber(cursor.getString(2));
                entry.setEmployed(cursor.getInt(3) == 1 ? true : false);
                entries.add(entry);

            }
            while (cursor.moveToNext());
        }

        return entries;
    }

    public void deleteOne(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("FORM_EMPLOYMENT", "ID=?", new String[] { String.valueOf(id) });
    }
}
