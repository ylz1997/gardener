package com.jw.consult.bean;

import java.util.List;
import java.util.Map;

/**
 * @author jw
 * @desc
 */
public class ConsultDataVO {

    private String cnslNm;
    private Long cnslId;
    private Long tmpltId;
    private int total;
    private List<ConsultRowVO> listConsultRow;
    private List<Map> listConsultRowResult;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCnslNm() {
        return cnslNm;
    }

    public void setCnslNm(String cnslNm) {
        this.cnslNm = cnslNm;
    }

    public Long getCnslId() {
        return cnslId;
    }

    public void setCnslId(Long cnslId) {
        this.cnslId = cnslId;
    }

    public Long getTmpltId() {
        return tmpltId;
    }

    public void setTmpltId(Long tmpltId) {
        this.tmpltId = tmpltId;
    }

    public List<ConsultRowVO> getListConsultRow() {
        return listConsultRow;
    }

    public void setListConsultRow(List<ConsultRowVO> listConsultRow) {
        this.listConsultRow = listConsultRow;
    }

    public List<Map> getListConsultRowResult() {
        return listConsultRowResult;
    }

    public void setListConsultRowResult(List<Map> listConsultRowResult) {
        this.listConsultRowResult = listConsultRowResult;
    }

    public ConsultRowVO createConsultRow(){
        return new ConsultRowVO();
    }

    public class ConsultRowVO{

        public ConsultRowVO(){}

        private Long recId;
        private Integer argeSeqNo;//取模板中的排序
        private List<ConsultRowsDataVO> listConsultRowsData;

        public Long getRecId() {
            return recId;
        }

        public void setRecId(Long recId) {
            this.recId = recId;
        }

        public Integer getArgeSeqNo() {
            return argeSeqNo;
        }

        public void setArgeSeqNo(Integer argeSeqNo) {
            this.argeSeqNo = argeSeqNo;
        }

        public List<ConsultRowsDataVO> getListConsultRowsData() {
            return listConsultRowsData;
        }

        public void setListConsultRowsData(List<ConsultRowsDataVO> listConsultRowsData) {
            this.listConsultRowsData = listConsultRowsData;
        }

        public ConsultRowsDataVO createConsultRowData(){
            return new ConsultRowsDataVO();
        }

        public class ConsultRowsDataVO{
            public ConsultRowsDataVO(){}

            private Long colmId;
            private String colmNm;
            private String colmTypeCd;
            private String colmCode;
            private String keyVal;


            public Long getColmId() {
                return colmId;
            }

            public void setColmId(Long colmId) {
                this.colmId = colmId;
            }

            public String getColmNm() {
                return colmNm;
            }

            public void setColmNm(String colmNm) {
                this.colmNm = colmNm;
            }

            public String getColmTypeCd() {
                return colmTypeCd;
            }

            public void setColmTypeCd(String colmTypeCd) {
                this.colmTypeCd = colmTypeCd;
            }

            public String getKeyVal() {
                return keyVal;
            }

            public void setKeyVal(String keyVal) {
                this.keyVal = keyVal;
            }

            public String getColmCode() {
                return colmCode;
            }

            public void setColmCode(String colmCode) {
                this.colmCode = colmCode;
            }
        }
    }
}
