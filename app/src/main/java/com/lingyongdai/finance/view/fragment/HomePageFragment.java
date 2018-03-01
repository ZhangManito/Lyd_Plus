package com.lingyongdai.finance.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.lingyongdai.finance.R;
import com.lingyongdai.finance.base.BaseFragment;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends BaseFragment {

    private static final String TAG = "HomePageFragment";

    public HomePageFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {


//        Observable.create(new ObservableOnSubscribe<Response>() {
//            @Override
//            public void subscribe(ObservableEmitter<Response> emitter) throws Exception {
//                Request.Builder builder = new Request.Builder()
//                        .url("http://api.avatardata.cn/MobilePlace/LookUp?key=ec47b85086be4dc8b5d941f5abd37a4e&mobileNumber=13021671512")
//                        .get();
//                Request request = builder.build();
//                Call call = new OkHttpClient().newCall(request);
//                Response response = call.execute();
//                Log.e(TAG, "Observable线程: "+Thread.currentThread().getName());
//                emitter.onNext(response);
//            }
//        }).map(new Function<Response, String>() {
//            @Override
//            public String apply(Response response) throws Exception {
//                if (response.isSuccessful()) {
//                    if (response.body() != null) {
//                        return response.body().string();
//                    }
//                }
//                return null;
//            }
//        }).observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        Log.e(TAG, "doOnNext: 保存成功：" + s + "\n");
//                        Log.e(TAG, "observeOn线程: "+Thread.currentThread().getName());
//                    }
//                }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        Log.e(TAG, "成功:" + s + "\n");
//                        Log.e(TAG, "Observe线程: "+Thread.currentThread().getName());
//                    }
//                });


//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                Log.e(TAG, "Observable thread is : " + Thread.currentThread().getName());
//                emitter.onNext(1);
//                emitter.onComplete();
//            }
//        })
//                .subscribeOn(Schedulers.newThread())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.e(TAG, "After observeOn(mainThread)，Current thread is " + Thread.currentThread().getName());
//                    }
//                })
//                .observeOn(Schedulers.io())
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.e(TAG, "After observeOn(io)，Current thread is " + Thread.currentThread().getName());
//                    }
//                });
    }

}
