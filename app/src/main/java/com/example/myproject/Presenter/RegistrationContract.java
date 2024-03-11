package com.example.myproject.Presenter;

import com.example.myproject.RoomDataBase.Registration;

import java.util.List;

public interface RegistrationContract {
    interface Model {

        interface OnFinishedListener {
            void onFinished(List<Registration> registrationArrayList);

            void onFailure(Throwable t);

        }

    }

    interface View {
        void showProgress();

        void hideProgress();

        void setDataToViews(List<Registration> registration);

        void onResponseFailure(String massage);

    }
        interface Presenter {

            void onDestroy();

            void requestDataFromServer();
            void sendDataToServer(Registration registration);

        }

}