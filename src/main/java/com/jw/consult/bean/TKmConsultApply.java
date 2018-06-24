package com.jw.consult.bean;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

public class TKmConsultApply implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_apply.CNSL_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    private Long cnslId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_apply.CNSL_NM
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    private String cnslNm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_apply.TMPLT_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    private Long tmpltId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_apply.ARGE_SEQNO
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    private Short argeSeqno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_apply.RMK
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    private String rmk;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_apply.OP_PRSN_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    private String opPrsnId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_apply.CRT_TIME
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp crtTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_apply.MODF_TIME
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp modfTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_apply.REGN_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    private String regnId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_km_consult_apply.TENANT_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    private String tenantId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public TKmConsultApply(Long cnslId, String cnslNm, Long tmpltId, Short argeSeqno, String rmk, String opPrsnId, Timestamp crtTime, Timestamp modfTime, String regnId, String tenantId) {
        this.cnslId = cnslId;
        this.cnslNm = cnslNm;
        this.tmpltId = tmpltId;
        this.argeSeqno = argeSeqno;
        this.rmk = rmk;
        this.opPrsnId = opPrsnId;
        this.crtTime = crtTime;
        this.modfTime = modfTime;
        this.regnId = regnId;
        this.tenantId = tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public TKmConsultApply() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_apply.CNSL_ID
     *
     * @return the value of t_km_consult_apply.CNSL_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public Long getCnslId() {
        return cnslId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_apply.CNSL_ID
     *
     * @param cnslId the value for t_km_consult_apply.CNSL_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setCnslId(Long cnslId) {
        this.cnslId = cnslId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_apply.CNSL_NM
     *
     * @return the value of t_km_consult_apply.CNSL_NM
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public String getCnslNm() {
        return cnslNm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_apply.CNSL_NM
     *
     * @param cnslNm the value for t_km_consult_apply.CNSL_NM
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setCnslNm(String cnslNm) {
        this.cnslNm = cnslNm == null ? null : cnslNm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_apply.TMPLT_ID
     *
     * @return the value of t_km_consult_apply.TMPLT_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public Long getTmpltId() {
        return tmpltId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_apply.TMPLT_ID
     *
     * @param tmpltId the value for t_km_consult_apply.TMPLT_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setTmpltId(Long tmpltId) {
        this.tmpltId = tmpltId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_apply.ARGE_SEQNO
     *
     * @return the value of t_km_consult_apply.ARGE_SEQNO
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public Short getArgeSeqno() {
        return argeSeqno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_apply.ARGE_SEQNO
     *
     * @param argeSeqno the value for t_km_consult_apply.ARGE_SEQNO
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setArgeSeqno(Short argeSeqno) {
        this.argeSeqno = argeSeqno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_apply.RMK
     *
     * @return the value of t_km_consult_apply.RMK
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public String getRmk() {
        return rmk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_apply.RMK
     *
     * @param rmk the value for t_km_consult_apply.RMK
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setRmk(String rmk) {
        this.rmk = rmk == null ? null : rmk.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_apply.OP_PRSN_ID
     *
     * @return the value of t_km_consult_apply.OP_PRSN_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public String getOpPrsnId() {
        return opPrsnId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_apply.OP_PRSN_ID
     *
     * @param opPrsnId the value for t_km_consult_apply.OP_PRSN_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setOpPrsnId(String opPrsnId) {
        this.opPrsnId = opPrsnId == null ? null : opPrsnId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_apply.CRT_TIME
     *
     * @return the value of t_km_consult_apply.CRT_TIME
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public Timestamp getCrtTime() {
        return crtTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_apply.CRT_TIME
     *
     * @param crtTime the value for t_km_consult_apply.CRT_TIME
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setCrtTime(Timestamp crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_apply.MODF_TIME
     *
     * @return the value of t_km_consult_apply.MODF_TIME
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public Timestamp getModfTime() {
        return modfTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_apply.MODF_TIME
     *
     * @param modfTime the value for t_km_consult_apply.MODF_TIME
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setModfTime(Timestamp modfTime) {
        this.modfTime = modfTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_apply.REGN_ID
     *
     * @return the value of t_km_consult_apply.REGN_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public String getRegnId() {
        return regnId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_apply.REGN_ID
     *
     * @param regnId the value for t_km_consult_apply.REGN_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setRegnId(String regnId) {
        this.regnId = regnId == null ? null : regnId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_km_consult_apply.TENANT_ID
     *
     * @return the value of t_km_consult_apply.TENANT_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_km_consult_apply.TENANT_ID
     *
     * @param tenantId the value for t_km_consult_apply.TENANT_ID
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }
}