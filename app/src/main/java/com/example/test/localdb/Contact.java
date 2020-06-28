package com.example.test.localdb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.util.TableInfo;

@Entity(tableName = "Contacts")
public class Contact {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;

    @NonNull
    @ColumnInfo(name="Name")
    private String name;

    @NonNull
    @ColumnInfo(name="Number")
    private String number;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public Contact(@NonNull int id, @NonNull String name, @NonNull String number, @Nullable byte[] image) {
        this.id = 0;
        this.name = name;
        this.number = number;
        if (image != null) {
            this.image = image;
        }
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String toString() {
        return "Contact name: " + name + ", Contact number: " + number;
    }

    public byte[] getImage() {
        return image;
    }
}
