package com.example.projetoipo.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ViaturaDao {
    @Insert
    fun inserirViatura(viatura:Viaturas)

    @Update
    fun atualizar(viatura: Viaturas)

    @Delete
    fun deletar(viatura: Viaturas): Int

    @Query("SELECT * FROM Viaturas ORDER BY cod ASC")
     fun listarTodas(): List<Viaturas>
}