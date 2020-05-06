package com.example.smkcodingproject1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profil.*

class ProfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        setTitle("Profil Anda")

        getData()

        btnUpdName.setOnClickListener { intentUpdateProfil() }
        btnPhone.setOnClickListener { dialPhoneNumber(tvPhone.text.toString()) }
        btnAbout.setOnClickListener { goToAbout() }
    }

    companion object {
        val REQUEST_CODE = 100
    }

    private fun getData() {
        val bundle = intent.extras

        val name = bundle?.getString("name")
        val age = bundle?.getString("age")
        val email = bundle?.getString("email")
        val phone = bundle?.getString("phone")
        val address = bundle?.getString("address")
        val gender = bundle?.getString("gender")

        tvName.text = name
        tvAge.text = age
        tvGender.text = gender
        tvEmail.text = email
        tvPhone.text = phone
        tvAddress.text = address
    }

    private fun intentUpdateProfil() {
        val intent = Intent(this, UpdateActivity::class.java)

        val updName = tvName.text.toString()
        val updAge = tvAge.text.toString()
        val updEmail = tvEmail.text.toString()
        val updPhone = tvPhone.text.toString()
        val updAddress = tvAddress.text.toString()
        intent.putExtra("name", updName)
        intent.putExtra("age", updAge)
        intent.putExtra("email", updEmail)
        intent.putExtra("phone", updPhone)
        intent.putExtra("address", updAddress)

        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val resName = data?.getStringExtra("name")
                val resAge = data?.getStringExtra("age")
                val resEmail = data?.getStringExtra("email")
                val resPhone = data?.getStringExtra("phone")
                val resAddress = data?.getStringExtra("address")
                val resGender = data?.getStringExtra("gender")
                tvName.text = resName
                tvAge.text = resAge
                tvEmail.text = resEmail
                tvPhone.text = resPhone
                tvAddress.text = resAddress
                tvGender.text = resGender
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

    private fun goToAbout() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_profil, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        menu(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun menu(itemId: Int) {
        if (itemId == R.id.aboutMe) {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        } else if (itemId == R.id.logout) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
