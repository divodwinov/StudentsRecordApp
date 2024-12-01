package com.example.sqliteconnectproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.ComponentActivity;

public class ActivityModifyStudents extends ComponentActivity implements View.OnClickListener {

    private EditText kelasText, namaText;
    private Button updateBtn, deleteBtn;
    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifydata);

        dbManager = new DBManager(this);
        dbManager.open();

        kelasText = findViewById(R.id.kelas_edittext);
        namaText = findViewById(R.id.nama_edittext);
        updateBtn = findViewById(R.id.btn_update);
        deleteBtn = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String kelas = intent.getStringExtra("kelas");
        String nama = intent.getStringExtra("nama");

        _id = Long.parseLong(id);
        kelasText.setText(kelas);
        namaText.setText(nama);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Use if-else instead of switch-case to avoid constant expression error
        if (v.getId() == R.id.btn_update) {
            String kelas = kelasText.getText().toString();
            String nama = namaText.getText().toString();
            dbManager.update(_id, kelas, nama);
            returnHome();
        } else if (v.getId() == R.id.btn_delete) {
            dbManager.delete(_id);
            returnHome();
        }
    }

    public void returnHome() {
        Intent homeIntent = new Intent(getApplicationContext(), ActivityDataStudents.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
