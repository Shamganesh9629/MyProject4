package com.example.myproject.RoomDataBase;

import android.app.Application;

import androidx.room.Room;

public class RegistrationDatabaseClient extends Application {
    public static  RegistrationDatabase registrationDatabase;
    @Override
    public void onCreate() {
        super.onCreate();
        registrationDatabase = Room.databaseBuilder(getApplicationContext(), RegistrationDatabase.class,
                "Registration_table").build();

    }
}
