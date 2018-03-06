package com.lingyongdai.finance.api;


import com.lingyongdai.finance.base.BaseSubscriber;
import com.lingyongdai.finance.bean.BaseEntity;
import com.lingyongdai.finance.bean.PlatformData;
import com.lingyongdai.finance.manager.ActivityStackManager;
import com.lingyongdai.finance.utils.NetworkUtil;
import com.lingyongdai.finance.utils.UrlUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by guoliang on 2018/2/28.
 */

public class HttpManager {

    private final Retrofit retrofit;
    private ContentService contentService;
    private HttpManager() {
        //缓存文件夹
        File cacheFile = new File(ActivityStackManager.getInstance().getTopActivity().getCacheDir().getAbsolutePath() + File.separator + "data", "NetCache");
        //缓存大小为10M
        int cacheSize = 10 * 1024 * 1024;
        //创建缓存对象
        Cache cache = new Cache(cacheFile, cacheSize);
        //拦截器
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            //直接读取缓存
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                String cache = request.header("Cache-Time");
                if (cache != null) {//缓存时间不为空
                    return response.newBuilder()
                            //移除原由的表头
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            //缓存时间
                            .header("Cache-Control", "max-age=" + cache)
                            .build();
                } else {
                    return response;
                }
            }
        };
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)//设置网络拦截器（当网络短路而返回缓存响应时不被调用）
                .cache(cache)//设置缓存地址
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS).build();//设置写入超时时间

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(UrlUtils.FINANCE_URL)
                .build();

        contentService = retrofit.create(ContentService.class);
    }
    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpManager INSTANCE = new HttpManager();
    }
    //获取单例
    public static HttpManager getInstance(){
        return SingletonHolder.INSTANCE;
    }
    private <T> void toSubscribe(Observable<T> o, Observer<T> s) {
        o.subscribeOn(Schedulers.io())//被观察线程
                //取消订阅线程
                .unsubscribeOn(Schedulers.io())
                //观察者线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
    /**
     * 获取理财页累计投资金额
     **/
    public void getAllAmountMonth(BaseSubscriber<PlatformData> subscriber, String appKey, long timestamp, String sign, String deviceId, String token) {

        Observable observable = contentService.getAllAmountApi(appKey, timestamp, sign, deviceId, token);
        toSubscribe(observable, subscriber);
    }

}
