package com.javahelps.helloworld

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the EditText etName
        var etName = findViewById<EditText>(R.id.etName)
        // Find the Button btnSayHello
        var btnSayHello = findViewById<Button>(R.id.btnSayHello);

        // Set onclick listener
        btnSayHello.setOnClickListener {
            Toast.makeText(this, "Hello ${etName.text}", Toast.LENGTH_LONG).show();
        }
    }
}
