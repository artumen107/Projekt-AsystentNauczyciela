package com.example.asystentnauczyciela.databaseModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marks_table")
data class Mark(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long,
    @ColumnInfo(name = "value") var value: Float,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "parent_student_id") var parentStudentId: Long
)