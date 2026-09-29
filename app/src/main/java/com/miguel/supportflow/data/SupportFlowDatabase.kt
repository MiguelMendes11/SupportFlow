package com.miguel.supportflow.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.miguel.supportflow.Chamado

@Database(
    entities = [Chamado::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ChamadoConverters::class)
abstract class SupportFlowDatabase : RoomDatabase() {

    abstract fun chamadoDao(): ChamadoDao

    companion object {
        @Volatile
        private var instancia: SupportFlowDatabase? = null

        fun getInstance(contexto: Context): SupportFlowDatabase {
            return instancia ?: synchronized(this) {
                instancia ?: Room.databaseBuilder(
                    contexto.applicationContext,
                    SupportFlowDatabase::class.java,
                    "supportflow.db"
                ).build().also { instancia = it }
            }
        }
    }
}
