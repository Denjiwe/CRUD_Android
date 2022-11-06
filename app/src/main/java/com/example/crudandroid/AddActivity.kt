package com.example.crudandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.crudandroid.databinding.ActivityAddBinding
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private var pessoa: Pessoa? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pessoa = intent.getSerializableExtra("Data") as? Pessoa

        if (pessoa == null) binding.btnAddOrUpdatePessoa.text = "Cadastrar Pessoa"
        else {
            binding.btnAddOrUpdatePessoa.text = "Atualizar Cadastro"
            binding.edNome.setText(pessoa?.nome.toString())
            binding.edEmail.setText(pessoa?.email.toString())
            binding.edIdade.setText(pessoa?.idade.toString())
            binding.edTelefone.setText(pessoa?.telefone.toString())
            //recuperar informações de idade e telefone

        }


        binding.btnAddOrUpdatePessoa.setOnClickListener { addPessoa() }
    }

    private fun addPessoa() {
        val nome = binding.edNome.text.toString()
        val email = binding.edEmail.text.toString()
        val idade = binding.edIdade.text.toString().toInt()
        val telefone = binding.edTelefone.text.toString().toInt()

        lifecycleScope.launch {
            if (pessoa == null) {
                val pessoa = Pessoa(nome= nome, email = email, idade = idade, telefone = telefone)
                AppDB(this@AddActivity).getPessoaDao().insertPessoa(pessoa)
                finish()
            } else {
                val u = Pessoa(nome, email, idade, telefone)
                u.id = pessoa?.id ?: 0
                AppDB(this@AddActivity).getPessoaDao().updatePessoa(u)
                finish()
            }
        }
    }
}