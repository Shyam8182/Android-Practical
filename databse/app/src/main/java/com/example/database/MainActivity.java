package com.example.database;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button btncreatedb, btninsert, btnmodify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btncreatedb = findViewById(R.id.btncreatedb);
        btninsert = findViewById(R.id.btninsert);
        btnmodify = findViewById(R.id.btnmodify);

        // Create Database
        btncreatedb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Database db = new Database(MainActivity.this);
                db.open();
                db.close();

                Toast.makeText(MainActivity.this,
                        "Database Created Successfully",
                        Toast.LENGTH_LONG).show();
            }
        });

        // Insert Page
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intr = new Intent(MainActivity.this, Second.class);
                startActivity(intr);
            }
        });

        // Modify Page
        btnmodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intr = new Intent(MainActivity.this, Modify.class);
                startActivity(intr);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
