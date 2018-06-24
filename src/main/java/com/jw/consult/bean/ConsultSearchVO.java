package com.jw.consult.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author jw
 * @desc
 */
public class ConsultSearchVO  implements Serializable {
    Long cnslId;
    Integer start;
    Integer length;
    Integer draw;

    public Long getCnslId() {
        return cnslId;
    }

    public void setCnslId(Long cnslId) {
        this.cnslId = cnslId;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    private List<InnerListBean> searchParams;

    public List<InnerListBean> getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(List<InnerListBean> searchParams) {
        this.searchParams = searchParams;
    }
}
