package com.example.crudandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudandroid.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var nAdapter: PessoaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setAdapter(list: List<Pessoa>) {
        nAdapter?.setData(list)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
           val pessoaList = AppDB(this@MainActivity).getPessoaDao().getAllPessoa()

           nAdapter = PessoaAdapter()
           binding.recyclerView.apply {
               layoutManager = LinearLayoutManager(this@MainActivity)
               adapter = nAdapter
               setAdapter(pessoaList)

               nAdapter?.setOnActionEditListener {
                   val intent = Intent(this@MainActivity, AddActivity::class.java)
                   intent.putExtra("Data", it)
                   startActivity(intent)
               }

               nAdapter?.setOnActionDeleteListener {
                   val builder = AlertDialog.Builder(this@MainActivity)
                   builder.setMessage("Tem certeza que gostaria de excluir?")
                   builder.setPositiveButton("Sim") { p0, p1 ->
                       lifecycleScope.launch {
                          AppDB(this@MainActivity).getPessoaDao().deletePessoa(it)
                          val list = AppDB(this@MainActivity).getPessoaDao().getAllPessoa()
                          setAdapter(list)
                       }
                       p0.dismiss()
                   }

                   builder.setNegativeButton("Não") {p0, p1 ->
                       p0.dismiss()
                   }

                   val dialog = builder.create()
                   dialog.show()
               }
           }
        }
    }
}

