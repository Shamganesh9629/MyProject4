package com.example.myproject.Presenter;

import android.widget.Toast;

import com.example.myproject.Activity.MainActivity;
import com.example.myproject.Api.ApiInterface;
import com.example.myproject.Api.RetrofitClient;
import com.example.myproject.RoomDataBase.Registration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationDetailsModel implements RegistrationContract.Model{

    public void getRegistration(OnFinishedListener onFinishedListener) {
        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<Registration>> call = apiInterface.getAllRegistration();
        call.enqueue(new Callback<List<Registration>>() {
            @Override
            public void onResponse(Call<List<Registration>> call, Response<List<Registration>> response) {
                if (response.isSuccessful()) {
                    List<Registration> registrationList = response.body();
                    onFinishedListener.onFinished(registrationList);
                }
            }

            @Override
            public void onFailure(Call<List<Registration>> call, Throwable t) {
                onFinishedListener.onFailure(t);

            }
        });
    }

    public void postRegistration(OnFinishedListener onFinishedListener, Registration registration) {
        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Registration> call = apiInterface.createPost(registration);
        call.enqueue(new Callback<Registration>() {
            @Override
            public void onResponse(Call<Registration> call, Response<Registration> response) {
                List<Registration> postSuccess = new ArrayList<>();
                postSuccess.add(response.body());
                onFinishedListener.onFinished(postSuccess);
            }

            @Override
            public void onFailure(Call<Registration> call, Throwable t) {
              onFinishedListener.onFailure(t);
            }
        });
    }

}
