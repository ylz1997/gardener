package com.jw.kids.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author jw
 * @desc
 */
public class TClassVO extends TClass{
    //课时数量
    private int amount;
    private List<TClassSchdule> schduleList;

    private BigDecimal price;

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
    }
}
