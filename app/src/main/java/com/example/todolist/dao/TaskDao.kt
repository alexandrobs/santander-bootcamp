package com.example.todolist.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.todolist.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAll() : MutableList<Task>

    @Query("SELECT * FROM TASK WHERE ID = :idTask")
    fun findById(idTask: Int) : Task

    @Insert
    fun insertTask(vararg task: Task)

    @Delete
    fun deleteTask(task: Task)
}
