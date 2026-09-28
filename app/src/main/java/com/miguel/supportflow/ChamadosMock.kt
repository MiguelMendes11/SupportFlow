package com.miguel.supportflow

object ChamadosMock {

    val chamados: List<Chamado> = listOf(
        Chamado(
            id = 1,
            titulo = "NFC-e não está emitindo",
            cliente = "Mercado Central",
            prioridade = Prioridade.ALTA,
            status = Status.EM_ANDAMENTO,
            descricao = "A emissão da NFC-e falha ao confirmar a autorização com a SEFAZ."
        ),
        Chamado(
            id = 2,
            titulo = "Impressora não imprime",
            cliente = "Loja Silva",
            prioridade = Prioridade.MEDIA,
            status = Status.PENDENTE,
            descricao = null
        ),
        Chamado(
            id = 3,
            titulo = "Erro ao acessar o sistema",
            cliente = "Farmácia Central",
            prioridade = Prioridade.BAIXA,
            status = Status.CONCLUIDO,
            descricao = "Usuário relatava mensagem de erro ao autenticar no login."
        )
    )
}
