package com.example.test.localdb;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);

    @Query("DELETE FROM Contacts")
    void deleteAll();

    @Delete
    void delete(Contact contact);

    @Query("DELETE FROM Contacts WHERE id = :pId")
    void deleteContact(int pId);

    @Query("SELECT * from Contacts ORDER BY name ASC")
    LiveData<List<Contact>> getAlphabetizedContacts();
}