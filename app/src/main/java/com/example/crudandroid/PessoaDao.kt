package com.example.crudandroid

import androidx.room.*

@Dao
interface PessoaDao {

    @Insert
    suspend fun insertPessoa(pessoa: Pessoa)

    @Query("SELECT * FROM pessoa ORDER BY id DESC")
    suspend fun getAllPessoa(): List<Pessoa>

    @Update
    suspend fun updatePessoa(pessoa: Pessoa)

    @Delete
    suspend fun deletePessoa(pessoa: Pessoa)
}