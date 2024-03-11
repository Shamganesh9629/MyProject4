package com.example.myproject.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.Api.ApiInterface;
import com.example.myproject.Api.RetrofitClient;
import com.example.myproject.Presenter.RegistrationContract;
import com.example.myproject.Presenter.RegistrationPresenter;
import com.example.myproject.R;
import com.example.myproject.RoomDataBase.Registration;
import com.example.myproject.RoomDataBase.RegistrationDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements RegistrationContract.View {
    public ImageButton add;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    public RecycleAdapter recycleAdapter;
    private RegistrationPresenter registrationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
        add = findViewById(R.id.addButton);
        progressBar = findViewById(R.id.pbloading);
        recyclerView = findViewById(R.id.recycle);
        registrationPresenter = new RegistrationPresenter(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (NetworkUtils.isNetworkConnected(this)) {
            registrationPresenter.requestDataFromServer();
        } else {
            getData();
        }
    }

    public void insertData(List<Registration> registrations) {
        class GetTasks extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                RegistrationDatabase.getInstance(getApplicationContext())
                        .registrationDao().insertAll(registrations);
                Log.d("doinback", registrations.toString());
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(HomeActivity.this,
                        "Data Insert Successful", Toast.LENGTH_SHORT).show();
            }
        }
        GetTasks gt = new GetTasks();
        gt.execute();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
       progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDataToViews(List<Registration> registration) {
        insertData(registration);
        setDataAdapter(registration);
    }

    @Override
    public void onResponseFailure(String massage) {
        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show();
    }

    public static class NetworkUtils {
        public static boolean isNetworkConnected(Context context) {
            ConnectivityManager cm = (ConnectivityManager) context.
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
    }

    public void getData() {
        class GetTasks extends AsyncTask<Void, Void, List<Registration>> {
            @Override
            protected List<Registration> doInBackground(Void... voids) {
                List<Registration> arrayData = RegistrationDatabase.
                        getInstance(getApplicationContext()).
                        registrationDao().getAllRegistration();
                return arrayData;
            }

            @Override
            protected void onPostExecute(List<Registration> arrayData) {
                super.onPostExecute(arrayData);
                setDataAdapter(arrayData);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registrationPresenter.onDestroy();
        registrationPresenter=null;
    }

    private void setDataAdapter(List<Registration> registerDataList) {
        recycleAdapter = new RecycleAdapter(HomeActivity.this, registerDataList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        recyclerView.setAdapter(recycleAdapter);
    }
}