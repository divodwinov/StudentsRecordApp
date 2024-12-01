package com.example.sqliteconnectproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nama Tabel
    public static final String TABLE_NAME = "tbl_student";

    // Nama Kolom dalam Tabel
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_KELAS = "kelas";
    public static final String COLUMN_NAMA = "nama";

    // Nama Database
    private static final String DB_NAME = "CBD.db";

    // Versi Database
    private static final int DB_VERSION = 1;

    // Query untuk Membuat Tabel
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_KELAS + " TEXT NOT NULL, "
                    + COLUMN_NAMA + " TEXT NOT NULL);";

    /**
     * Constructor DatabaseHelper
     *
     * @param context Context aplikasi
     */
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * Dipanggil saat database pertama kali dibuat.
     * @param db Objek SQLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Eksekusi perintah SQL untuk membuat tabel
        db.execSQL(CREATE_TABLE);
    }

    /**
     * Dipanggil saat database di-upgrade (misalnya, versi database berubah).
     * @param db         Objek SQLiteDatabase
     * @param oldVersion Versi lama database
     * @param newVersion Versi baru database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Hapus tabel lama jika ada
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Buat ulang tabel
        onCreate(db);
    }
}
