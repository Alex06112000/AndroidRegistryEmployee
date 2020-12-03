package com.alex.registryemployers.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alex.registryemployers.model.Employer

@Database(entities = [Employer::class], version = 1, exportSchema = false)
abstract class EmployerDatabase: RoomDatabase() {

    abstract fun employerDao(): EmployerDao

    companion object{
        @Volatile
        private var INSTANCE: EmployerDatabase? = null

        fun getDatabase(context: Context): EmployerDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmployerDatabase::class.java,
                    "employer_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}