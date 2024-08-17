package com.example.scud.ui.auth;

import android.content.Intent;
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
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.scud.MainActivity;
import com.example.scud.R;
import com.example.scud.model.DataModel;
import com.example.scud.ui.show.ShowFragment;


public class AuthFragment extends Fragment {

    private AuthViewModel viewModel;
    private EditText editTextlogin;
    private EditText editTextpassword;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_auth, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        Button buttonSignIn = view.findViewById(R.id.buttonSignIn);
        editTextlogin = view.findViewById(R.id.login);
        editTextpassword = view.findViewById(R.id.password);

        Bundle arguments = getArguments();

        if (arguments != null) {
            String login = arguments.getString("login");
            String password = arguments.getString("password");
            editTextlogin.setText(login);
            editTextpassword.setText(password);
        }

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
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("login", dataModel.getLogin());
                intent.putExtra("email", dataModel.getEmail());
                intent.putExtra("id", dataModel.getPk());
                intent.putExtra("firstName", dataModel.getFirstName());
                intent.putExtra("lastName", dataModel.getLastName());
                intent.putExtra("middleName", dataModel.getMiddleName());

                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
            }
        });
    }
}