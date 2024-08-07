package com.example.scud.ui.show;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.scud.model.DataModel;
import com.example.scud.repository.DataRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowViewModel extends ViewModel {

    private DataRepository qrRepository;
    private MutableLiveData<DataModel> qrResponseLiveData;

    public ShowViewModel() {
        qrRepository = new DataRepository();
        qrResponseLiveData = new MutableLiveData<>();
    }

    public LiveData<DataModel> getQrCode(int userId) {
        qrRepository.getQrCode(userId).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    qrResponseLiveData.setValue(response.body());
                } else {
                    qrResponseLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                qrResponseLiveData.setValue(null);
            }
        });
        return qrResponseLiveData;
    }
}