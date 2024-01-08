package com.example.rahmatantravel.dialog.dialogAyat;

import android.app.Activity;
import android.util.Log;

import com.example.rahmatantravel.helper.LoadingHelper;
import com.example.rahmatantravel.model.DataAyatItem;
import com.example.rahmatantravel.network.ApiClient;
import com.example.rahmatantravel.network.ApiService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AyatPresenter {
    private IViewAyat iView;
    private Activity activity;
    public LoadingHelper loadingHelper;
    private ApiService apiService;

    public AyatPresenter(IViewAyat iView, Activity activity) {
        this.iView = iView;
        this.activity = activity;
        loadingHelper = new LoadingHelper(activity);
    }

    public void onGetDetailAyat(String nomor){
        loadingHelper.startLoading();
        apiService = ApiClient.getRetrofit().create(ApiService.class);
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(
          apiService.getDetailAyat(nomor)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeWith(new DisposableObserver<List<DataAyatItem>>() {
              @Override
              public void onNext(List<DataAyatItem> dataAyatItems) {
                iView.onGetDataAyat(dataAyatItems);
              }

              @Override
              public void onError(Throwable e) {
                  Log.d("asda", ""+e.toString());
                  iView.onErrorMsg(e.toString());
                  loadingHelper.stopLoading();
              }

              @Override
              public void onComplete() {
                loadingHelper.stopLoading();
              }
          }));
    }
}
