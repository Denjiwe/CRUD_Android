package com.example.crudandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.crudandroid.databinding.ActivityAddBinding
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddPessoa.setOnClickListener { addPessoa() }
    }

    private fun addPessoa() {
        val nome = binding.edNome.text.toString()
        val email = binding.edEmail.text.toString()
        val idade = binding.edIdade.text.toString().toInt()
        val telefone = binding.edTelefone.text.toString().toInt()

        lifecycleScope.launch {
            val pessoa = Pessoa(nome= nome, email = email, idade = idade, telefone = telefone)
            AppDB(this@AddActivity).getPessoaDao().insertPessoa(pessoa)
            finish()
        }
    }
}