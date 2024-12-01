package com.example.sqliteconnectproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityDataStudents extends AppCompatActivity {

    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    // Kolom database yang akan ditampilkan
    final String[] from = new String[]{
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_KELAS,
            DatabaseHelper.COLUMN_NAMA
    };

    // ID view yang akan dihubungkan dengan data
    final int[] to = new int[]{
            R.id.id,
            R.id.kelas,
            R.id.nama
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Memilih layout
        setContentView(R.layout.activity_datastudents);

        // Inisialisasi DBManager
        dbManager = new DBManager(this);
        dbManager.open();

        // Ambil data dari database
        Cursor cursor = dbManager.fetch();

        // Inisialisasi ListView
        listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        // Adapter untuk menghubungkan data dengan ListView
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.activity_fragment,
                cursor,
                from,
                to,
                0
        );

        listView.setAdapter(adapter);

        // Listener untuk item di ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                // Ambil data dari item yang dipilih
                TextView idTextView = view.findViewById(R.id.id);
                TextView kelasTextView = view.findViewById(R.id.kelas);
                TextView namaTextView = view.findViewById(R.id.nama);

                String id = idTextView.getText().toString();
                String kelas = kelasTextView.getText().toString();
                String nama = namaTextView.getText().toString();

                // Intent untuk mengirim data ke halaman Edit
                Intent modifyIntent = new Intent(ActivityDataStudents.this, ActivityModifyStudents.class);
                modifyIntent.putExtra("id", id);
                modifyIntent.putExtra("kelas", kelas);
                modifyIntent.putExtra("nama", nama);
                startActivity(modifyIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Memasukkan menu ke dalam Activity
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handler untuk item menu
        int id = item.getItemId();
        if (id == R.id.add_record) {
            Intent addIntent = new Intent(this, ActivityAddStudents.class);
            startActivity(addIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
