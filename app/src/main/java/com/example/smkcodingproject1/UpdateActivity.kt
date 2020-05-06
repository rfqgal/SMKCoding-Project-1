package com.example.smkcodingproject1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { finish() }
        toolbar.setTitle("Update Profil")

        val intentData = intent.extras
        val name = intentData?.getString("name")
        val age = intentData?.getString("age")
        val email = intentData?.getString("email")
        val phone = intentData?.getString("phone")
        val address = intentData?.getString("address")

        edtUpdName.setText(name)
        edtUpdAge.setText(age)
        edtUpdEmail.setText(email)
        edtUpdPhone.setText(phone)
        edtUpdAddress.setText(address)

        btnUpdSave.setOnClickListener { saveUpdate() }
    }

    private fun saveUpdate() {
        val edtName = edtUpdName.text.toString()
        val edtAge = edtUpdAge.text.toString()
        val edtEmail = edtUpdEmail.text.toString()
        val edtPhone = edtUpdPhone.text.toString()
        val edtAddress = edtUpdAddress.text.toString()
        val spGender = spUpdGender.selectedItem.toString()

        val checkName = edtName.isEmpty()
        val checkAge = edtAge.isEmpty()
        val checkAge2 = edtAge == "0"
        val checkEmail = edtEmail.isEmpty()
        val checkEmail2 = edtEmail.length < 9
        val checkPhone = edtPhone.isEmpty()
        val checkPhone2 = edtPhone.length < 10
        val checkAddress = edtAddress.isEmpty()
        val checkGender = spGender.equals("Pilih Jenis Kelamin", ignoreCase = true)
        if (!(checkName || checkAge || checkAge2 || checkEmail || checkEmail2 || checkPhone || checkPhone2 || checkAddress || checkGender)) {
            val result = Intent()
            result.putExtra("name", edtName)
            result.putExtra("age", edtAge)
            result.putExtra("email", edtEmail)
            result.putExtra("phone", edtPhone)
            result.putExtra("address", edtAddress)
            result.putExtra("gender", spGender)
            setResult(Activity.RESULT_OK, result)
            finish()
        } else if (checkGender) {
            Toast.makeText(this, "Anda harus memilih Gender", Toast.LENGTH_SHORT).show()
        } else if (checkAge2) {
            Toast.makeText(this, "Umur tidak boleh 0", Toast.LENGTH_SHORT).show()
        } else if (checkEmail2) {
            Toast.makeText(this, "Email Anda tidak valid", Toast.LENGTH_SHORT).show()
        } else if (checkPhone2) {
            Toast.makeText(this, "Nomor telepon harus lebih dari 10 digit", Toast.LENGTH_SHORT).show()
        } else {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
