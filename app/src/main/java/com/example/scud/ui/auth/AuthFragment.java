package com.example.scud.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.scud.R;


public class AuthFragment extends Fragment {

    private AuthViewModel viewModel;
    private EditText editTextlogin;
    private EditText editTextpassword;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_auth, container, false);

        View view = inflater.inflate(R.layout.fragment_auth, container, false);
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        Button buttonSignIn = view.findViewById(R.id.buttonSignIn);
        editTextlogin = view.findViewById(R.id.login);
        editTextpassword = view.findViewById(R.id.password);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = editTextlogin.getText().toString();
                String password = editTextpassword.getText().toString();

                if (!login.isEmpty() && !password.isEmpty()) {
                    authenticateUser(login, password);
                } else {
                    Toast.makeText(getContext(), "Пожалуйста введите логин и пароль", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void authenticateUser(String login, String password) {
        viewModel.authenticate(login, password).observe(getViewLifecycleOwner(), dataModel -> {
            if (dataModel != null) {
                Toast.makeText(getContext(), "Добро пожаловать", Toast.LENGTH_SHORT).show();
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.navigation_account);
            } else {
                Toast.makeText(getContext(), "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
            }
        });
    }
}