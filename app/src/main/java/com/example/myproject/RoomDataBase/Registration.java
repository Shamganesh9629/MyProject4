package com.example.myproject.RoomDataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Registration_table")
public class Registration {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo (name = "name")
    public String name;
    @ColumnInfo(name = "gender")
    public String gender;
    @ColumnInfo(name = "mobileNumber")
    public String mobileNumber;
    @ColumnInfo(name = "eMail")
    public String eMail;


    public Registration(String name, String gender, String mobileNumber, String eMail) {
        this.name = name;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.eMail = eMail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
