package com.miguel.supportflow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChamadoRepositoryMock : ChamadoRepository {

    private val chamados = MutableStateFlow(ChamadosMock.chamados)

    override fun observeChamados(): Flow<List<Chamado>> = chamados.asStateFlow()

    override suspend fun obterPorId(id: Int): Chamado? =
        chamados.value.find { it.id == id }

    override suspend fun salvar(chamado: Chamado) {
        chamados.value = if (chamados.value.any { it.id == chamado.id }) {
            chamados.value.map { if (it.id == chamado.id) chamado else it }
        } else {
            chamados.value + chamado
        }
    }

    override suspend fun excluir(chamado: Chamado) {
        chamados.value = chamados.value.filterNot { it.id == chamado.id }
    }
}
