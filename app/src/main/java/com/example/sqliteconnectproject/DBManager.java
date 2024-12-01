package com.example.sqliteconnectproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    /**
     * Constructor untuk DBManager
     *
     * @param context Context aplikasi
     */
    public DBManager(Context context) {
        this.context = context;
    }

    /**
     * Membuka koneksi ke database.
     *
     * @return DBManager instance
     * @throws SQLException jika terjadi kesalahan saat membuka database
     */
    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    /**
     * Menutup koneksi ke database.
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * Menambahkan data ke tabel.
     *
     * @param kelas Nama kelas
     * @param nama  Nama siswa
     */
    public void insert(String kelas, String nama) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.COLUMN_KELAS, kelas);
        contentValue.put(DatabaseHelper.COLUMN_NAMA, nama);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    /**
     * Mengambil semua data dari tabel.
     *
     * @return Cursor yang menunjuk ke data
     */
    public Cursor fetch() {
        String[] columns = new String[]{
                DatabaseHelper.COLUMN_ID,
                DatabaseHelper.COLUMN_KELAS,
                DatabaseHelper.COLUMN_NAMA
        };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns,
                null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     * Memperbarui data dalam tabel berdasarkan ID.
     *
     * @param id    ID dari data yang akan diperbarui
     * @param kelas Nama kelas baru
     * @param nama  Nama siswa baru
     * @return Jumlah baris yang diperbarui
     */
    public int update(long id, String kelas, String nama) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_KELAS, kelas);
        contentValues.put(DatabaseHelper.COLUMN_NAMA, nama);
        return database.update(DatabaseHelper.TABLE_NAME, contentValues,
                DatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    /**
     * Menghapus data dalam tabel berdasarkan ID.
     *
     * @param id ID dari data yang akan dihapus
     */
    public void delete(long id) {
        database.delete(DatabaseHelper.TABLE_NAME,
                DatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
