package com.example.contact_app_recycler_view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class ContactDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact_details)

        val ivDetailProfile = findViewById<ImageView>(R.id.ivDetailProfile)
        val tvDetailName = findViewById<TextView>(R.id.tvDetailName)
        val tvDetailPhone = findViewById<TextView>(R.id.tvDetailPhone)
        val btnCall = findViewById<MaterialButton>(R.id.btnCall)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.detailToolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
        
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val name = intent.getStringExtra("EXTRA_NAME") ?: "Unknown"
        val phone = intent.getStringExtra("EXTRA_PHONE") ?: "No Number"
        val imageUriString = intent.getStringExtra("EXTRA_IMAGE_URI")

        tvDetailName.text = name
        tvDetailPhone.text = phone

        if (imageUriString != null) {
            ivDetailProfile.setImageURI(Uri.parse(imageUriString))
        } else {
            ivDetailProfile.setImageResource(R.drawable.ic_person)
        }

        btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phone")
            }
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.appBar)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, 0)
            insets
        }
    }
}
