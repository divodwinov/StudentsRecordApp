package com.example.sqliteconnectproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityAddStudents extends Activity implements View.OnClickListener {

    private Button addTodoBtn;
    private EditText kelasEditText;
    private EditText namaEditText;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set judul halaman
        setTitle("Tambah Data");

        // Menghubungkan layout
        setContentView(R.layout.activity_addstudents);

        // Inisialisasi input dan tombol
        kelasEditText = findViewById(R.id.kelas_edittext);
        namaEditText = findViewById(R.id.nama_edittext);
        addTodoBtn = findViewById(R.id.add_record);

        // Membuat objek DBManager dan membuka koneksi database
        dbManager = new DBManager(this);
        dbManager.open();

        // Mengatur tombol dengan listener
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_record) {
            // Mengambil data input dari user
            final String kelas = kelasEditText.getText().toString();
            final String nama = namaEditText.getText().toString();

            // Memasukkan data ke database melalui DBManager
            dbManager.insert(kelas, nama);

            // Berpindah ke halaman daftar siswa
            Intent main = new Intent(ActivityAddStudents.this, ActivityDataStudents.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(main);
        }
    }
}
