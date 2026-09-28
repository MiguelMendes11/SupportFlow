package com.miguel.supportflow

import java.io.Serializable

enum class Prioridade(val rotulo: String) {
    ALTA("Alta"),
    MEDIA("Média"),
    BAIXA("Baixa")
}

enum class Status(val rotulo: String) {
    PENDENTE("Pendente"),
    EM_ANDAMENTO("Em andamento"),
    CONCLUIDO("Concluído")
}

data class Chamado(
    val id: Int,
    val titulo: String,
    val cliente: String,
    val prioridade: Prioridade,
    val status: Status,
    val descricao: String? = null
) : Serializable
