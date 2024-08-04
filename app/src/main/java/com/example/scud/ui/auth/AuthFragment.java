package com.example.scud.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.scud.R;


public class AuthFragment extends Fragment {

    private AuthViewModel viewModel;

    TextView errorLogin;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_auth, container, false);

        View view = inflater.inflate(R.layout.fragment_auth, container, false);
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        viewModel.getData().observe(getViewLifecycleOwner(), dataModel -> {
            errorLogin = view.findViewById(R.id.erroLogin);
            errorLogin.setText("ERROR LOGING");
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button nextToAccount = view.findViewById(R.id.buttonSignIn);

        nextToAccount.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_account);
        });

    }
}