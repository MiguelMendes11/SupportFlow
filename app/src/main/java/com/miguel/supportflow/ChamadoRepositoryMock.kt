package com.miguel.supportflow

class ChamadoRepositoryMock : ChamadoRepository {

    override fun getChamados(): List<Chamado> = ChamadosMock.chamados
}
