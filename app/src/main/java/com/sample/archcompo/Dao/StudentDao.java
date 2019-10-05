package com.sample.archcompo.Dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.sample.archcompo.model.Student;

import java.util.List;

@Dao
public interface StudentDao {

    @Query("SELECT * From student_Db ")
    DataSource.Factory<Integer,Student> getAllStudents();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Student student);

    @Delete
    void delete(Student student);

    @Update
    void update(Student student);


}
