package com.example.scud.ui.users;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scud.R;
import com.example.scud.model.UsersList;
import com.example.scud.ui.auth.AuthViewModel;
import com.example.scud.ui.recycler.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class UsersFragment extends Fragment {

    UsersViewModel viewModel;
    private UsersViewModel mViewModel;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<UsersList> usersList;

    public static UsersFragment newInstance() {
        return new UsersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);


        viewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        recyclerView = view.findViewById(R.id.usersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);


        viewModel.getUsersLiveData().observe(getViewLifecycleOwner(), new Observer<List<UsersList>>() {
            @Override
            public void onChanged(List<UsersList> usersLists) {
                if (usersLists != null) {
                    adapter.setData(usersLists);
                    Log.d("My fragment", "Data received " + usersLists.size() + " items");
                } else {
                    Log.d("My fragment", "Something broke");
                }
            }
        });
        return view;
    }
}