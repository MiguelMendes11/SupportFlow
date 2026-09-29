package com.miguel.supportflow.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.miguel.supportflow.Chamado
import kotlinx.coroutines.flow.Flow

@Dao
interface ChamadoDao {

    @Query("SELECT * FROM chamados ORDER BY id DESC")
    fun observeChamados(): Flow<List<Chamado>>

    @Query("SELECT * FROM chamados WHERE id = :id")
    suspend fun buscarPorId(id: Int): Chamado?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(chamado: Chamado)

    @Update
    suspend fun atualizar(chamado: Chamado)
}
