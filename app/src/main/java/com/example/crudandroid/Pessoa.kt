package com.example.crudandroid

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pessoa (
    var nome:String = "",
    var email:String = "",
    var idade:Int = 0,
    var telefone:Int = 0
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}