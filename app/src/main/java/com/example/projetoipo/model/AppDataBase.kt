package com.example.projetoipo.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_6_7 = object : Migration(6, 7) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS `viaturas` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                `cod` INTEGER NOT NULL,  
                `prefixo` TEXT NOT NULL,                            
            )
        """)
    }
}

@Database(entities = [Usuario::class, Viaturas::class], version = 7)
abstract class AppDataBase : RoomDatabase() {
    abstract fun dao(): UsuarioDao
    abstract fun daoViaturas(): ViaturaDao

    companion object {
        private var INSTANCE: AppDataBase? = null
        fun getDataBase(contexto: Context): AppDataBase {
            return if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        contexto.applicationContext,
                        AppDataBase::class.java,
                        name = "db_ipo"
                    ).addMigrations(MIGRATION_6_7).build()
                }
                INSTANCE as AppDataBase
            } else {
                INSTANCE as AppDataBase
            }
        }
    }
}