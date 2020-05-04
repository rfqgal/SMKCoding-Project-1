package com.example.smkcodingproject1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        edtUpdName.setText(name)

        btnUpdSave.setOnClickListener { saveUpdate() }
    }

    private fun saveUpdate() {
        val edtName = edtUpdName.text.toString()
        if (!edtName.isEmpty()) {
            val result = Intent()
            result.putExtra("name", edtName)
            setResult(Activity.RESULT_OK, result)
        } else {
            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }
}
