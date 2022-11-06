package com.example.crudandroid

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Pessoa (
    var nome:String = "",
    var email:String = "",
    var idade:Int = 0,
    var telefone:Int = 0
) : Serializable {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}