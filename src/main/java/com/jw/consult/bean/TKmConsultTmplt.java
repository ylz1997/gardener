package com.jw.consult.bean;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

public class TKmConsultTmplt implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_tmplt.TMPLT_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    private Long tmpltId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_tmplt.TMPLT_NM
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    private String tmpltNm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_tmplt.CATL_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    private Long catlId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_tmplt.ARGE_SEQNO
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    private Short argeSeqno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_tmplt.AUTH_REGN
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    private String authRegn;

    public String getAuthRegnNm() {
        return authRegnNm;
    }

    public void setAuthRegnNm(String authRegnNm) {
        this.authRegnNm = authRegnNm;
    }

    private String authRegnNm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_tmplt.OP_PRSN_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    private String opPrsnId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_tmplt.CRT_TIME
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp crtTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_tmplt.MODF_TIME
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp modfTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_tmplt.TENANT_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    private String tenantId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_tmplt.RMK
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    private String rmk;

    /**
     * 查询组合条件
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp startTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp endTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_km_consult_tmplt
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public TKmConsultTmplt(Long tmpltId, String tmpltNm, Long catlId, Short argeSeqno, String authRegn, String opPrsnId, Timestamp crtTime, Timestamp modfTime, String tenantId, String rmk) {
        this.tmpltId = tmpltId;
        this.tmpltNm = tmpltNm;
        this.catlId = catlId;
        this.argeSeqno = argeSeqno;
        this.authRegn = authRegn;
        this.opPrsnId = opPrsnId;
        this.crtTime = crtTime;
        this.modfTime = modfTime;
        this.tenantId = tenantId;
        this.rmk = rmk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_km_consult_tmplt
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public TKmConsultTmplt() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_tmplt.TMPLT_ID
     *
     * @return the value of t_km_consult_tmplt.TMPLT_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public Long getTmpltId() {
        return tmpltId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_tmplt.TMPLT_ID
     *
     * @param tmpltId the value for t_km_consult_tmplt.TMPLT_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setTmpltId(Long tmpltId) {
        this.tmpltId = tmpltId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_tmplt.TMPLT_NM
     *
     * @return the value of t_km_consult_tmplt.TMPLT_NM
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public String getTmpltNm() {
        return tmpltNm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_tmplt.TMPLT_NM
     *
     * @param tmpltNm the value for t_km_consult_tmplt.TMPLT_NM
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setTmpltNm(String tmpltNm) {
        this.tmpltNm = tmpltNm == null ? null : tmpltNm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_tmplt.CATL_ID
     *
     * @return the value of t_km_consult_tmplt.CATL_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public Long getCatlId() {
        return catlId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_tmplt.CATL_ID
     *
     * @param catlId the value for t_km_consult_tmplt.CATL_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setCatlId(Long catlId) {
        this.catlId = catlId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_tmplt.ARGE_SEQNO
     *
     * @return the value of t_km_consult_tmplt.ARGE_SEQNO
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public Short getArgeSeqno() {
        return argeSeqno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_tmplt.ARGE_SEQNO
     *
     * @param argeSeqno the value for t_km_consult_tmplt.ARGE_SEQNO
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setArgeSeqno(Short argeSeqno) {
        this.argeSeqno = argeSeqno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_tmplt.AUTH_REGN
     *
     * @return the value of t_km_consult_tmplt.AUTH_REGN
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public String getAuthRegn() {
        return authRegn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_tmplt.AUTH_REGN
     *
     * @param authRegn the value for t_km_consult_tmplt.AUTH_REGN
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setAuthRegn(String authRegn) {
        this.authRegn = authRegn == null ? null : authRegn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_tmplt.OP_PRSN_ID
     *
     * @return the value of t_km_consult_tmplt.OP_PRSN_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public String getOpPrsnId() {
        return opPrsnId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_tmplt.OP_PRSN_ID
     *
     * @param opPrsnId the value for t_km_consult_tmplt.OP_PRSN_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setOpPrsnId(String opPrsnId) {
        this.opPrsnId = opPrsnId == null ? null : opPrsnId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_tmplt.CRT_TIME
     *
     * @return the value of t_km_consult_tmplt.CRT_TIME
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public Timestamp getCrtTime() {
        return crtTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_tmplt.CRT_TIME
     *
     * @param crtTime the value for t_km_consult_tmplt.CRT_TIME
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setCrtTime(Timestamp crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_tmplt.MODF_TIME
     *
     * @return the value of t_km_consult_tmplt.MODF_TIME
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public Timestamp getModfTime() {
        return modfTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_tmplt.MODF_TIME
     *
     * @param modfTime the value for t_km_consult_tmplt.MODF_TIME
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setModfTime(Timestamp modfTime) {
        this.modfTime = modfTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_tmplt.TENANT_ID
     *
     * @return the value of t_km_consult_tmplt.TENANT_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_tmplt.TENANT_ID
     *
     * @param tenantId the value for t_km_consult_tmplt.TENANT_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_tmplt.RMK
     *
     * @return the value of t_km_consult_tmplt.RMK
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public String getRmk() {
        return rmk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_tmplt.RMK
     *
     * @param rmk the value for t_km_consult_tmplt.RMK
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setRmk(String rmk) {
        this.rmk = rmk == null ? null : rmk.trim();
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public TKmConsultTmplt setStartTime(Timestamp startTime) {
        this.startTime = startTime;
        return this;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public TKmConsultTmplt setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        return this;
    }
}