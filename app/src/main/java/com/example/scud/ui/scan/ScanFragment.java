package com.example.scud.ui.scan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.scud.R;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;


public class ScanFragment extends Fragment {

    private Button scanQRCodeButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_scan, container, false);

        scanQRCodeButton = view.findViewById(R.id.buttonScanQRCode);

        final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
                result -> {
                    if(result.getContents() == null) {
                        Toast.makeText(requireActivity(), "Cancelled", Toast.LENGTH_LONG).show();
                    } else {
                        String scannedData = result.getContents();
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(scannedData));
                        startActivity(browserIntent);
                        Toast.makeText(requireActivity(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                    }
                });

        scanQRCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanOptions options = new ScanOptions();
                options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
                options.setPrompt("Отсканируйте QR код");
                options.setCameraId(0);
                options.setBeepEnabled(false);
                options.setBarcodeImageEnabled(true);
                barcodeLauncher.launch(options);
            }
        });

        return view;
    }
}