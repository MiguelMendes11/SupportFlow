package com.miguel.supportflow

import androidx.room.Entity
import androidx.room.PrimaryKey
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

@Entity(tableName = "chamados")
data class Chamado(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    val cliente: String,
    val prioridade: Prioridade,
    val status: Status,
    val descricao: String? = null
) : Serializable
