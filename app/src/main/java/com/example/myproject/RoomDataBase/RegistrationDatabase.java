package com.example.myproject.RoomDataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Registration.class}, version = 1)
public abstract class RegistrationDatabase extends RoomDatabase {
    private static RegistrationDatabase instance;
    public abstract RegistrationDao registrationDao();

    public static synchronized RegistrationDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            RegistrationDatabase.class, "Registration_table")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
