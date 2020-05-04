package com.example.smkcodingproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var inName: String = ""
    private var inAge: String = ""
    private var inEmail: String = ""
    private var inPhone: String = ""
    private var inAddress: String = ""
    private var inGender: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("App Biodata")

        setDataSpinnerGender()

        btnSave.setOnClickListener{validasiInput()}
    }

    private fun intentProfil() {
        val intent = Intent(this, ProfilActivity::class.java)

        val bundle = Bundle()
        bundle.putString("name", inName)
        bundle.putString("age", inAge)
        bundle.putString("email", inEmail)
        bundle.putString("phone", inPhone)
        bundle.putString("address", inAddress)
        bundle.putString("gender", inGender)

        intent.putExtras(bundle)

        startActivity(intent)
    }

    private fun validasiInput() {
        inName = edtName.text.toString()
        inAge = edtAge.text.toString()
        inEmail = edtEmail.text.toString()
        inPhone = edtPhone.text.toString()
        inAddress = edtAddress.text.toString()
        inGender = spGender.selectedItem.toString()

        when {
            inName.isEmpty() -> edtName.error = "Nama tidak boleh kosong"
            inGender.equals("Pilih Jenis Kelamin", ignoreCase = true) -> toast("Jenis kelamin harus dipilih")
            inAge.isEmpty() -> edtAge.error = "Umur harus diisi"
            inEmail.isEmpty() -> edtEmail.error = "Email tidak boleh kosong"
            inPhone.isEmpty() -> edtPhone.error = "Nomor telepon tidak boleh kosong"
            inAddress.isEmpty() -> edtAddress.error = "Alamat tidak boleh kosong"

            else -> {
                toast("Navigasi ke halaman profil")
                intentProfil()
            }
        }
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setDataSpinnerGender() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spGender.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
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
        } else if (itemId == R.id.exit) {
            finish()
        }
    }
}
