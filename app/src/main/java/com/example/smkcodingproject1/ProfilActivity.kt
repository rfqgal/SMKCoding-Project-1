package com.example.smkcodingproject1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profil.*

class ProfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        getData()

        btnUpdName.setOnClickListener{intentUpdateProfil()}
        btnPhone.setOnClickListener { dialPhoneNumber(tvPhone.text.toString()) }
    }

    companion object {
        val REQUEST_CODE = 100
    }

    private fun getData() {
        val bundle = intent.extras

        val name = bundle?.getString("name")
        val email = bundle?.getString("email")
        val phone = bundle?.getString("phone")
        val address = bundle?.getString("address")
        val gender = bundle?.getString("gender")

        tvName.text = name
        tvGender.text = gender
        tvEmail.text = email
        tvPhone.text = phone
        tvAddress.text = address
    }

    private fun intentUpdateProfil() {
        val intent = Intent(this, UpdateActivity::class.java)

        val updName = tvName.text.toString()
        intent.putExtra("name", updName)

        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val result = data?.getStringExtra("name")
                tvName.text = result
            } else {
                Toast.makeText(this, "Edit data failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
