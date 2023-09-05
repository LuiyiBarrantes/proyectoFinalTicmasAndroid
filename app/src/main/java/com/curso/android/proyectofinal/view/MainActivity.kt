package com.curso.android.proyectofinal.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.curso.android.proyectofinal.model.TextsToCompare
import com.curso.android.proyectofinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TextComparisonViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater
        )
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(TextComparisonViewModel::class.java)

        viewModel.resultText.observe(this) { result ->
            binding.TextView.text = result
        }
        binding.button2.setOnClickListener {
            val text1 = binding.EditText1.text.toString()
            val text2 = binding.EditText2.text.toString()
            val model = TextsToCompare(text1, text2)
            viewModel.compareTexts(model)
        }
    }
}
