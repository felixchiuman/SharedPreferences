package com.example.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlinsharedpreference"

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        binding.btnSave.setOnClickListener {
            val id: Int? = binding.etInputId.text.toString().toIntOrNull()
            val name: String = binding.etInputName.text.toString()
            if (id == null || name == ""){
                Toast.makeText(this, "Data Kosong", Toast.LENGTH_SHORT).show()
            }
            else{
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putInt("id_key",id)
                editor.putString("name_key", name)
                editor.apply()
                Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
            }
        }
        
        binding.btnView.setOnClickListener { 
            val sharedIdValue = sharedPreferences.getInt("id_key",0)
            val sharedNameValue = sharedPreferences.getString("name_key", "defaultname")
            if (sharedIdValue.equals(0) && sharedNameValue.equals("defaultname")){
                binding.tvShowName.setText("Default Name : ${sharedNameValue}").toString()
                binding.tvShowId.setText("Default Id : ${sharedIdValue.toString()}")
                Toast.makeText(this, "Data View Kosong", Toast.LENGTH_SHORT).show()
            }
            else{
                binding.tvShowName.setText(sharedNameValue).toString()
                binding.tvShowId.setText(sharedIdValue.toString())
                Toast.makeText(this, "Data View Ditampilkan", Toast.LENGTH_SHORT).show()
            }
        }
        
        binding.btnClear.setOnClickListener { 
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            binding.tvShowName.setText("")
            binding.tvShowId.setText("")
            Toast.makeText(this, "Data Clear", Toast.LENGTH_SHORT).show()
        }
    }
}