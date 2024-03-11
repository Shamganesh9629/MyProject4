package com.example.myproject.RoomDataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RegistrationDao {

    @Insert
    void insert(Registration registration);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Registration> registration);

    @Update
    void update(Registration registration);
    @Delete
    void delete(Registration registration);

    @Query("SELECT * FROM Registration_table ")
    List<Registration >getAllRegistration();
}
