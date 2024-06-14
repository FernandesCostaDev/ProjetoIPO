package com.example.projetoipo.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsuarioDao {
    @Insert
    fun inserir(usuario: Usuario)

   @Query("SELECT * FROM Usuario ORDER BY nome ASC")
    fun buscarRegistro(): List<Usuario>

    @Query("SELECT * FROM Usuario WHERE nome = :nome")
    fun buscarRegistro2(nome: String): List<Usuario>

    @Delete
    fun deletar(usuario: Usuario):Int

    @Update
    fun atualizar(usuario: Usuario)

}

