package com.miguel.supportflow

import kotlinx.coroutines.flow.Flow

interface ChamadoRepository {

    fun observeChamados(): Flow<List<Chamado>>

    suspend fun obterPorId(id: Int): Chamado?

    suspend fun salvar(chamado: Chamado)

    suspend fun excluir(chamado: Chamado)
}
