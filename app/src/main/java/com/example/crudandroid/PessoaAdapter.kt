package com.example.crudandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PessoaAdapter : RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder>() {
    private var lista = mutableListOf<Pessoa>()
    private var actionUpdate: ((Pessoa)-> Unit)? = null
    private var actionDelete: ((Pessoa)-> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item_pessoa_view_holder, parent, false)

        return PessoaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {
        val pessoa = lista[position]


        holder.tvNome.text = pessoa.nome
        holder.tvEmail.text = pessoa.email
        holder.tvIdade.text = pessoa.idade.toString()
        holder.tvTelefone.text = pessoa.telefone.toString()

        //adicionar campos de idade e telefone


        holder.actionUpdate.setOnClickListener { actionUpdate?.invoke(pessoa) }
        holder.actionDelete.setOnClickListener { actionDelete?.invoke(pessoa) }
    }

    override fun getItemCount() = lista.size

    fun setData(data: List<Pessoa>) {
        lista.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    fun setOnActionEditListener(callback: (Pessoa) -> Unit) {
        this.actionUpdate = callback
    }

    fun setOnActionDeleteListener(callback: (Pessoa) -> Unit) {
        this.actionDelete = callback
    }

    class PessoaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNome: TextView = itemView.findViewById<TextView>(R.id.tv_nome)
        val tvEmail: TextView = itemView.findViewById<TextView>(R.id.tv_email)
        val tvIdade: TextView = itemView.findViewById<TextView>(R.id.tv_idade)
        val tvTelefone: TextView = itemView.findViewById<TextView>(R.id.tv_telefone)
        val actionUpdate: ImageView = itemView.findViewById(R.id.action_update)
        val actionDelete: ImageView = itemView.findViewById(R.id.action_delete)
    }

}