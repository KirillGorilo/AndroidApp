package com.example.scud.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.scud.R;
import com.example.scud.databinding.FragmentAccountBinding;
import com.example.scud.ui.SharedViewModel;
import com.example.scud.ui.auth.AuthViewModel;


public class AccountFragment extends Fragment {
    private AuthViewModel viewModel;
    private SharedViewModel sharedViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        EditText firstName = view.findViewById(R.id.editTextFirstName);
        EditText middleName = view.findViewById(R.id.editTextMiddleName);
        EditText lastName = view.findViewById(R.id.editTextLastName);
        EditText login = view.findViewById(R.id.editTextLogin);
        EditText email = view.findViewById(R.id.editTextMail);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        Bundle args = getArguments();
        if (args != null) {
            login.setHint(args.getString("login"));
            firstName.setHint(args.getString("firstName"));
            lastName.setHint(args.getString("lastName"));
            middleName.setHint(args.getString("middleName"));
            email.setHint(args.getString("email"));
            sharedViewModel.setSelectedData(args.getString("id"));
        }
        viewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);


        Button buttonAllUsers = view.findViewById(R.id.buttonAllUsers);
        buttonAllUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.navigation_users);
            }
        });
        return view;
    }
}