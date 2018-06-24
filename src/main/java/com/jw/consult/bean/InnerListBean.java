package com.jw.consult.bean;

import java.io.Serializable;

/**
 * @author jw
 * @desc
 */
public class InnerListBean implements Serializable{
    private Long columnId;
    private String type;
    private String value;
    private String fieldType;

    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
}