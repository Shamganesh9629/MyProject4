package com.example.myproject.Presenter;

import com.example.myproject.RoomDataBase.Registration;

import java.util.List;

public class RegistrationPresenter implements
        RegistrationContract.Presenter , RegistrationContract.Model.OnFinishedListener,
        RegistrationContract.Model {
    private RegistrationContract.View registrationView;
    private RegistrationDetailsModel registrationModel;
    public RegistrationPresenter(RegistrationContract.View registrationView ){
        this.registrationView=registrationView;
        registrationModel = new RegistrationDetailsModel();

    }

    @Override
    public void onFinished(List<Registration> registrationArrayList) {
      registrationView.hideProgress();
      registrationView.setDataToViews(registrationArrayList);
    }

    @Override
    public void onFailure(Throwable t) {
    registrationView.onResponseFailure(t.getLocalizedMessage());

    }

    @Override
    public void onDestroy() {
        registrationView =null;
        registrationModel =null;
    }


    @Override
    public void requestDataFromServer() {
        registrationView.showProgress();
        registrationModel.getRegistration(this);


    }

    @Override
    public void sendDataToServer(Registration registration) {
        registrationView.showProgress();
        registrationModel.postRegistration(this , registration);
    }

}
