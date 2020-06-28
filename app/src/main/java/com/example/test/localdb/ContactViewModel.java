package com.example.test.localdb;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository mRepository;

    private LiveData<List<Contact>> mAllContacts;

    public ContactViewModel(Application application) {
        super(application);
        mRepository = new ContactRepository(application);
        mAllContacts = mRepository.getAllWords();
    }

    public Contact getContactById(int id) {
        for (int i = 0; i < mAllContacts.getValue().size(); i++) {
            if (mAllContacts.getValue().get(i).getId() == id) {
                return mAllContacts.getValue().get(i);
            }
        }
        return null;
    }

    public LiveData<List<Contact>> getAllContacts() {
        return mAllContacts;
    }

    public void delete(Contact contact) {
        mRepository.delete(contact);
    }
    public void deleteContact(Contact contact) {
        mRepository.deleteContact(contact);
    }

    public void insert(Contact contact) {
        mRepository.insert(contact);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }
}
