package com.REGISTRADO.activity4

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.REGISTRADO.activity4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        loadStateFromPreferences()

        binding.save.setOnClickListener{
            saveStatePreference()

            Toast.makeText(this, "Settings Saved!", Toast.LENGTH_LONG).show()
        }



    }
    private fun saveStatePreference() {
        val editor = sharedPreferences.edit()
        editor.putString("email", binding.emailtitle.text.toString())
        editor.putString("nickname", binding.nicknames.text.toString())
        editor.putBoolean("allowsnotfication", binding.checkbox.isChecked)
        editor.putInt("SelectedTheme", binding.ThemeButton.checkedRadioButtonId)
        editor.apply()
    }

    private fun loadStateFromPreferences() {
        binding.emailtitle.setText(sharedPreferences.getString("email", ""))
        binding.nicknames.setText(sharedPreferences.getString("nickname", ""))
        binding.checkbox.isChecked = sharedPreferences.getBoolean("allowsnotfication", false)

        val selectedThemeId = sharedPreferences.getInt("SelectedTheme", -1)
        if (selectedThemeId != -1)
            binding.ThemeButton.check(selectedThemeId)
    }
}