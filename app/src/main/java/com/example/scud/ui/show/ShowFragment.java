package com.example.scud.ui.show;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.scud.R;
import com.example.scud.databinding.FragmentShowBinding;
import com.example.scud.model.DataModel;
import com.example.scud.repository.DataRepository;
import com.example.scud.ui.account.AccountFragment;
import com.example.scud.ui.auth.AuthViewModel;

import javax.security.auth.callback.Callback;

public class ShowFragment extends Fragment {

    private ImageView qrCode;
    private AuthViewModel viewModel;
    private ShowViewModel showViewModel;
    private int id;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, container, false);

        Button showQR = view.findViewById(R.id.buttonUpdateQR);
        showViewModel = new ViewModelProvider(this).get(ShowViewModel.class);
        viewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        viewModel.getData();


        viewModel.getData().observe(getViewLifecycleOwner(), new Observer<DataModel>() {
            @Override
            public void onChanged(DataModel dataModel) {
                id = dataModel.getPk();
            }
        });



        showQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrCode = view.findViewById(R.id.qrCode);
                showViewModel.getQrCode(id).observe(getViewLifecycleOwner(), new Observer<DataModel>() {
                    @Override
                    public void onChanged(DataModel dataModel) {
                        if (dataModel != null) {
                            QRCodeUtils.generateQRCode(dataModel.getUrl(), qrCode);
                        }
                    }
                });
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}