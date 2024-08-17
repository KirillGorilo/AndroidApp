package com.example.scud.ui.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scud.R;
import com.example.scud.ui.auth.AuthViewModel;

public class RegistrationFragment extends Fragment {

    private RegistrationViewModel viewModel;
    private EditText editTextLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(RegistrationViewModel.class);

        Button registrationButton = view.findViewById(R.id.registrationButton);
        editTextLogin = view.findViewById(R.id.regLogin);
        editTextEmail = view.findViewById(R.id.regEmail);
        editTextPassword = view.findViewById(R.id.regPassword);
        Bundle bundle = new Bundle();

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = editTextLogin.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                bundle.putString("login", login);
                bundle.putString("password", password);

                if (!login.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
                    registrationUser(login, email, password, bundle);
                }
            }
        });

        return view;
    }

    private void registrationUser(String username, String email, String password, Bundle bundle) {
        viewModel.registration(username, email, password).observe(getViewLifecycleOwner(), dataModel -> {
            if (dataModel.getErrorMessage() == null) {
                Toast.makeText(getContext(), "Успешная регистрация!!! Теперь необходимо войти в систему", Toast.LENGTH_SHORT).show();

                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_auth_fragment);
                navController.navigate(R.id.nav_log, bundle);
            } else {
                Toast.makeText(getContext(), dataModel.getErrorMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}