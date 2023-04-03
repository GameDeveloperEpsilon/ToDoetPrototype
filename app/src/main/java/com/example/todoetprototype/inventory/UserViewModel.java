package com.example.todoetprototype.inventory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    private final MutableLiveData<UserModel> userData = new MutableLiveData<>(UserModel.getInstance());

    public UserViewModel() {

        System.out.println("User View Model Created");
    }


    public LiveData<UserModel> getUserData() {
        return userData;
    }

    public void updateUser(int coins) {
        UserModel updatedUserModel = userData.getValue();
        assert updatedUserModel != null;
        updatedUserModel.setCoins(coins);
        userData.setValue(updatedUserModel);
    }
}
