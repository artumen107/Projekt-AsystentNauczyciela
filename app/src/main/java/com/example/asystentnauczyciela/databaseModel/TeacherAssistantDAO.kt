package com.example.asystentnauczyciela.databaseModel

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TeacherAssistantDAO {

    //Subject DAO INTERFACE
    @Insert
    fun insertSubject(subject: Subject)

    @Query("SELECT * FROM subjects_table")
    fun getAllSubjects(): LiveData<List<Subject>>

    @Query("DELETE FROM subjects_table")
    fun deleteAllSubjects()

    @Query("DELETE FROM subjects_table WHERE subject_name = :subjectName")
    fun deleteSubject(subjectName: String)

    @Query("SELECT COUNT(*) FROM subjects_table ")
    fun countAllSubjects(): Int


    //Student DAO INTERFACE
    @Insert
    fun insertStudent(student: Student)

    @Query("SELECT * FROM students_table WHERE parent_class_tag = :parentClass")
    fun getAllStudents(parentClass: String): LiveData<List<Student>>

    @Query("DELETE FROM students_table")
    fun deleteAllStudents()

    @Query("DELETE FROM students_table WHERE " +
            "name = :name AND " +
            "surname = :surname AND " +
            "albumNumber = :albumNumber")
    fun deleteStudent(name: String, surname: String, albumNumber: Long)

    @Query("SELECT COUNT(*) FROM students_table WHERE parent_class_tag = :parentClass")
    fun countAllStudents(parentClass: String): Int


    //Class DAO INTERFACE
    @Insert
    fun insertClass(myClass: Class)

    @Query("SELECT * FROM classes_table " +
            "WHERE parent_subject_name = :parentSubject " +
            "ORDER BY CASE " +
            "WHEN day_of_week = 'Monday' THEN 1 " +
            "WHEN day_of_week = 'Tuesday' THEN 2 " +
            "WHEN day_of_week = 'Wednesday' THEN 3 " +
            "WHEN day_of_week = 'Thursday' THEN 4 " +
            "WHEN day_of_week = 'Friday' THEN 5 " +
            "END ASC")
    fun getAllClasses(parentSubject: String): LiveData<List<Class>>

    @Query("DELETE FROM classes_table WHERE id = :id")
    fun deleteClass(id: Long)

    @Query("SELECT * FROM classes_table WHERE id = :id")
    fun getClass(id: Long): Class

    @Query("SELECT COUNT(*) FROM classes_table WHERE parent_subject_name = :parentSubject ")
    fun countAllClasses(parentSubject: String): Int


    //Mark DAO INTERFACE
    @Insert
    fun insertMark(mark: Mark)

    @Query("SELECT * FROM marks_table " +
            "WHERE parent_student_id = :parentStudent")
    fun getAllMarks(parentStudent: Long): LiveData<List<Mark>>

    @Query("DELETE FROM marks_table WHERE id = :id")
    fun deleteMark(id: Long)

    @Query("SELECT COUNT(*) FROM marks_table " +
            "WHERE parent_student_id = :parentStudent")
    fun countAllMarks(parentStudent: Long): Int



}