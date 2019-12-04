package trexmoney.it.moneyWallet.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> meseCorrente;


    public HomeViewModel() {
        meseCorrente = new MutableLiveData<>();
        meseCorrente.setValue("Dicembre");
    }

    public LiveData<String> getMeseCorrente(){
        return meseCorrente;
    }
}