package com.javahelps.customfont

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create a TypeFace using the font file
        val typeface = Typeface.createFromAsset(assets, "baamini.ttf")

        txtMessage.text = "tzf;fk;"   // Welcome in Tamil
        // Set the typeface
        txtMessage.typeface = typeface
    }
}
