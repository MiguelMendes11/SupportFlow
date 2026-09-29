package com.miguel.supportflow.data

import androidx.room.TypeConverter
import com.miguel.supportflow.Prioridade
import com.miguel.supportflow.Status

class ChamadoConverters {

    @TypeConverter
    fun prioridadeParaTexto(prioridade: Prioridade): String = prioridade.name

    @TypeConverter
    fun textoParaPrioridade(texto: String): Prioridade = Prioridade.valueOf(texto)

    @TypeConverter
    fun statusParaTexto(status: Status): String = status.name

    @TypeConverter
    fun textoParaStatus(texto: String): Status = Status.valueOf(texto)
}
