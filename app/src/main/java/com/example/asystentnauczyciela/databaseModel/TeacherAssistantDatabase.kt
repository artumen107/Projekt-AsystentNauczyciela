package com.example.asystentnauczyciela.databaseModel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subject::class, Student::class, Class::class, Mark::class], version = 15,
    exportSchema = false)
abstract class TeacherAssistantDatabase : RoomDatabase() {

    abstract val dao: TeacherAssistantDAO
    companion object {
        @Volatile
        private var INSTANCE: TeacherAssistantDatabase? = null

        fun getInstance(context: Context): TeacherAssistantDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TeacherAssistantDatabase::class.java,
                        "teacher_assistant_database")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

