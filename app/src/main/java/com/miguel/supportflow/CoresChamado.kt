package com.miguel.supportflow

import androidx.annotation.ColorRes

object CoresChamado {

    @ColorRes
    fun corDaPrioridade(prioridade: Prioridade): Int = when (prioridade) {
        Prioridade.ALTA -> R.color.prioridade_alta
        Prioridade.MEDIA -> R.color.prioridade_media
        Prioridade.BAIXA -> R.color.prioridade_baixa
    }

    @ColorRes
    fun corDoStatus(status: Status): Int = when (status) {
        Status.PENDENTE -> R.color.status_pendente
        Status.EM_ANDAMENTO -> R.color.status_em_andamento
        Status.CONCLUIDO -> R.color.status_concluido
    }
}
