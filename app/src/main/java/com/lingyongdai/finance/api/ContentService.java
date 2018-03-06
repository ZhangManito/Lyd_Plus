package com.lingyongdai.finance.api;

import com.lingyongdai.finance.bean.BaseEntity;
import com.lingyongdai.finance.bean.PlatformData;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by guoliang on 2018/3/5.
 */

public interface ContentService {
    @POST("/home/getIndexResponseLyd")
    Observable<PlatformData> getAllAmountApi(@Query("appKey") String appKey, @Query("timestamp") long timestamp, @Query("sign") String sign,
                                           @Query("deviceId") String deviceId, @Query("token") String token);
}
