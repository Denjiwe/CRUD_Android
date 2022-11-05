package com.example.crudandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PessoaAdapter : RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder>() {
    private var lista = mutableListOf<Pessoa>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item_pessoa_view_holder, parent, false)

        return PessoaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {
        val user = lista[position]
        val idade = user.idade
        val telefone = user.telefone


        holder.tvNome.text = user.nome
        holder.tvEmail.text = user.email
        holder.tvIdade.text.toString().toInt(idade)
        holder.tvTelefone.text.toString().toInt(telefone)
    }

    override fun getItemCount() = lista.size

    fun setData(data: List<Pessoa>) {
        lista.apply {
            clear()
            addAll(data)
        }
    }

    class PessoaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNome: TextView = itemView.findViewById<TextView>(R.id.tv_nome)
        val tvEmail: TextView = itemView.findViewById<TextView>(R.id.tv_email)
        val tvIdade: TextView = itemView.findViewById<TextView>(R.id.tv_idade)
        val tvTelefone: TextView = itemView.findViewById<TextView>(R.id.tv_telefone)


    }

}