package com.jw.kids.bean;

import com.jw.base.DateUtil;
import com.jw.base.GeneralException;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author jw
 * @desc
 */
public class TClassVO extends TClass{

    private String strCrtTime;

    public String getStrCrtTime() {
        return strCrtTime;
    }

    public void setStrCrtTime(String strCrtTime) throws GeneralException {
        this.strCrtTime = strCrtTime;
        setCrtTime(DateUtil.convertDateStringToTimestamp(strCrtTime,"yyyy-MM-dd HH:mm:ss"));
    }

    //课时数量
/*    private int amount;
    private List<TClassSchdule> schduleList;

    private BigDecimal price;

    private String classPackageNm;

    public String getClassPackageNm() {
        return classPackageNm;
    }

    public void setClassPackageNm(String classPackageNm) {
        this.classPackageNm = classPackageNm;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<TClassSchdule> getSchduleList() {
        return schduleList;
    }

    public void setSchduleList(List<TClassSchdule> schduleList) {
        this.schduleList = schduleList;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }*/
}
