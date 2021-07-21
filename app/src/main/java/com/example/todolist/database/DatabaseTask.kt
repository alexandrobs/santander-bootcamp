package com.example.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.dao.TaskDao
import com.example.todolist.model.Task

@Database(entities = arrayOf(Task::class), version = 1)
abstract class DatabaseTask : RoomDatabase (){
    abstract fun taskDao(): TaskDao

    companion object {

        private const val DB_NAME = "database-task"
        private var INSTANCE: DatabaseTask? = null

        fun getDatabase(context: Context): DatabaseTask? {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, DatabaseTask::class.java, DB_NAME
                )
                    .build()
            }
            return INSTANCE
        }
    }
}