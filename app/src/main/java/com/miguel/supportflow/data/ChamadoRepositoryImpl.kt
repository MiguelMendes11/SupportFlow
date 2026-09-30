package com.miguel.supportflow.data

import com.miguel.supportflow.Chamado
import com.miguel.supportflow.ChamadoRepository
import kotlinx.coroutines.flow.Flow

class ChamadoRepositoryImpl(
    private val chamadoDao: ChamadoDao
) : ChamadoRepository {

    override fun observeChamados(): Flow<List<Chamado>> = chamadoDao.observeChamados()

    override suspend fun obterPorId(id: Int): Chamado? = chamadoDao.buscarPorId(id)

    override suspend fun salvar(chamado: Chamado) {
        if (chamado.id == 0) {
            chamadoDao.inserir(chamado)
        } else {
            chamadoDao.atualizar(chamado)
        }
    }

    override suspend fun excluir(chamado: Chamado) {
        chamadoDao.excluir(chamado)
    }
}
