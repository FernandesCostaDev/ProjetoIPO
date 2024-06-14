package com.example.projetoipo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Viaturas(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "cod") val cod: Int,
    @ColumnInfo(name = "prefixo") val prefixo: String,
)
