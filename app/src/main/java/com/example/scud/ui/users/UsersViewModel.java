package com.example.scud.ui.users;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.scud.model.UsersList;
import com.example.scud.repository.DataRepository;

import java.util.List;

public class UsersViewModel extends ViewModel {
    private DataRepository repository;
    private LiveData<List<UsersList>> usersLiveData;

    public UsersViewModel() {
        repository = new DataRepository();
        usersLiveData = repository.getUsers();
    }

    public LiveData<List<UsersList>> getUsersLiveData() {
        return usersLiveData;
    }
}