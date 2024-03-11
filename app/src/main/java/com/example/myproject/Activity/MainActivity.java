package com.example.myproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.Api.ApiInterface;
import com.example.myproject.Api.RetrofitClient;
import com.example.myproject.Presenter.RegistrationContract;
import com.example.myproject.Presenter.RegistrationPresenter;
import com.example.myproject.R;
import com.example.myproject.RoomDataBase.Registration;
import com.example.myproject.RoomDataBase.RegistrationDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RegistrationContract.View  {
    public EditText nameEdt, genderEdt, mobileNumberEdt, eMailEdt;
    public Button registerButtons;
    private RegistrationPresenter registrationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEdt = (EditText) findViewById(R.id.editTextText);
        genderEdt = (EditText) findViewById(R.id.editTextText2);
        mobileNumberEdt = (EditText) findViewById(R.id.editTextText3);
        eMailEdt = (EditText) findViewById(R.id.editTextText4);
        registerButtons = findViewById(R.id.registerButton);
        registrationPresenter = new RegistrationPresenter(this);

        registerButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameEdt.getText().toString().isEmpty() && genderEdt.getText().toString().isEmpty()
                        && mobileNumberEdt.getText().toString().isEmpty()
                        && eMailEdt.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                postData(nameEdt.getText().toString(),
                        genderEdt.getText().toString(),
                        mobileNumberEdt.getText().toString(),
                        eMailEdt.getText().toString());

            }
        });
    }

    private void postData(String name, String gender, String mobileNumber, String eMail) {
        Registration registration = new Registration(name, gender, mobileNumber, eMail);
        registrationPresenter.sendDataToServer(registration);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDataToViews(List<Registration> registration) {
        Toast.makeText(this, "Data Added To Api", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponseFailure(String massage) {
        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registrationPresenter.onDestroy();
        registrationPresenter=null;
    }
}