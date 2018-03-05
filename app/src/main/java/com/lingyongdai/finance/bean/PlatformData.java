package com.lingyongdai.finance.bean;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by guoliang on 2018/3/5.
 */

public class PlatformData {
    private int code;
    private String message;
    private double investAmountSum;//累计投资总金额
    private double investProfitSum;//累计赚取总收益
    private int peopleCount;//累计总人数
    private String imgMain;//主图片
    private String imgVice;//附图片
    private String imgHref;//链接
    private String imgMiddle;//未登录活动图片
    private String imgNewSpecial;//新手专享标图片
    private String imgNewExperienceTy;//新手体验标图片
    private Integer newBidBtn;//新手标开关，0关闭，1打开


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getInvestAmountSum() {
        return investAmountSum;
    }

    public void setInvestAmountSum(double investAmountSum) {
        this.investAmountSum = investAmountSum;
    }

    public double getInvestProfitSum() {
        return investProfitSum;
    }

    public void setInvestProfitSum(double investProfitSum) {
        this.investProfitSum = investProfitSum;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getImgMain() {
        return imgMain;
    }

    public void setImgMain(String imgMain) {
        this.imgMain = imgMain;
    }

    public String getImgVice() {
        return imgVice;
    }

    public void setImgVice(String imgVice) {
        this.imgVice = imgVice;
    }

    public String getImgHref() {
        return imgHref;
    }

    public void setImgHref(String imgHref) {
        this.imgHref = imgHref;
    }

    public String getImgMiddle() {
        return imgMiddle;
    }

    public void setImgMiddle(String imgMiddle) {
        this.imgMiddle = imgMiddle;
    }

    public String getImgNewSpecial() {
        return imgNewSpecial;
    }

    public void setImgNewSpecial(String imgNewSpecial) {
        this.imgNewSpecial = imgNewSpecial;
    }

    public String getImgNewExperienceTy() {
        return imgNewExperienceTy;
    }

    public void setImgNewExperienceTy(String imgNewExperienceTy) {
        this.imgNewExperienceTy = imgNewExperienceTy;
    }

    public Integer getNewBidBtn() {
        return newBidBtn;
    }

    public void setNewBidBtn(Integer newBidBtn) {
        this.newBidBtn = newBidBtn;
    }
}
