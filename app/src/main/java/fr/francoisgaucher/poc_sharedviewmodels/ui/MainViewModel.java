package fr.francoisgaucher.poc_sharedviewmodels.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> mToast;

    public MainViewModel() {
        Log.d("Francois", ">> New MainViewModel Instance");
        mText = new MutableLiveData<>();
        mToast = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public void fireToast(){
        mToast.postValue("Ceci est un nouveau toast");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getToastMessage() {
        return mToast;
    }


}
