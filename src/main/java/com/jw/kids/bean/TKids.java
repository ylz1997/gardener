package com.jw.kids.bean;

import com.jw.base.DateUtil;
import com.jw.base.GeneralException;

import java.sql.Timestamp;

public class TKids {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.k_id
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private Long kId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.ch_nm
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private String chNm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.en_nm
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private String enNm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.sex
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private String sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.birthday
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private Timestamp birthday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.contract_no
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private Long contractNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.p_nm
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private String pNm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.p_relation
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private String pRelation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.phone
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private Long phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.address
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.class_id
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private Long classId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.consultant_id
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private Long consultantId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.if_app_account
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private Byte ifAppAccount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.telephone_time
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private String telephoneTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.amount
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private Integer amount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.crt_time
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private Timestamp crtTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.modf_time
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private Timestamp modfTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.operator
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private Long operator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kids.rmk
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    private String rmk;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_kids
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public TKids(Long kId, String chNm, String enNm, String sex, Timestamp birthday, Long contractNo, String pNm, String pRelation, Long phone, String address, Long classId, Long consultantId, Byte ifAppAccount, String telephoneTime, Integer amount, Timestamp crtTime, Timestamp modfTime, Long operator, String rmk) {
        this.kId = kId;
        this.chNm = chNm;
        this.enNm = enNm;
        this.sex = sex;
        this.birthday = birthday;
        this.contractNo = contractNo;
        this.pNm = pNm;
        this.pRelation = pRelation;
        this.phone = phone;
        this.address = address;
        this.classId = classId;
        this.consultantId = consultantId;
        this.ifAppAccount = ifAppAccount;
        this.telephoneTime = telephoneTime;
        this.amount = amount;
        this.crtTime = crtTime;
        this.modfTime = modfTime;
        this.operator = operator;
        this.rmk = rmk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_kids
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public TKids() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.k_id
     *
     * @return the value of t_kids.k_id
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public Long getkId() {
        return kId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.k_id
     *
     * @param kId the value for t_kids.k_id
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setkId(Long kId) {
        this.kId = kId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.ch_nm
     *
     * @return the value of t_kids.ch_nm
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public String getChNm() {
        return chNm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.ch_nm
     *
     * @param chNm the value for t_kids.ch_nm
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setChNm(String chNm) {
        this.chNm = chNm == null ? null : chNm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.en_nm
     *
     * @return the value of t_kids.en_nm
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public String getEnNm() {
        return enNm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.en_nm
     *
     * @param enNm the value for t_kids.en_nm
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setEnNm(String enNm) {
        this.enNm = enNm == null ? null : enNm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.sex
     *
     * @return the value of t_kids.sex
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public String getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.sex
     *
     * @param sex the value for t_kids.sex
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.birthday
     *
     * @return the value of t_kids.birthday
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public String getBirthday() throws GeneralException {
        return DateUtil.convertTimestampToString(birthday,"yyyy-MM-dd");
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.birthday
     *
     * @param birthday the value for t_kids.birthday
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.contract_no
     *
     * @return the value of t_kids.contract_no
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public Long getContractNo() {
        return contractNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.contract_no
     *
     * @param contractNo the value for t_kids.contract_no
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setContractNo(Long contractNo) {
        this.contractNo = contractNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.p_nm
     *
     * @return the value of t_kids.p_nm
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public String getpNm() {
        return pNm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.p_nm
     *
     * @param pNm the value for t_kids.p_nm
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setpNm(String pNm) {
        this.pNm = pNm == null ? null : pNm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.p_relation
     *
     * @return the value of t_kids.p_relation
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public String getpRelation() {
        return pRelation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.p_relation
     *
     * @param pRelation the value for t_kids.p_relation
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setpRelation(String pRelation) {
        this.pRelation = pRelation == null ? null : pRelation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.phone
     *
     * @return the value of t_kids.phone
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public Long getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.phone
     *
     * @param phone the value for t_kids.phone
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setPhone(Long phone) {
        this.phone = phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.address
     *
     * @return the value of t_kids.address
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.address
     *
     * @param address the value for t_kids.address
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.class_id
     *
     * @return the value of t_kids.class_id
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public Long getClassId() {
        return classId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.class_id
     *
     * @param classId the value for t_kids.class_id
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.consultant_id
     *
     * @return the value of t_kids.consultant_id
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public Long getConsultantId() {
        return consultantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.consultant_id
     *
     * @param consultantId the value for t_kids.consultant_id
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setConsultantId(Long consultantId) {
        this.consultantId = consultantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.if_app_account
     *
     * @return the value of t_kids.if_app_account
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public Byte getIfAppAccount() {
        return ifAppAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.if_app_account
     *
     * @param ifAppAccount the value for t_kids.if_app_account
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setIfAppAccount(Byte ifAppAccount) {
        this.ifAppAccount = ifAppAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.telephone_time
     *
     * @return the value of t_kids.telephone_time
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public String getTelephoneTime() {
        return telephoneTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.telephone_time
     *
     * @param telephoneTime the value for t_kids.telephone_time
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setTelephoneTime(String telephoneTime) {
        this.telephoneTime = telephoneTime == null ? null : telephoneTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.amount
     *
     * @return the value of t_kids.amount
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.amount
     *
     * @param amount the value for t_kids.amount
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.crt_time
     *
     * @return the value of t_kids.crt_time
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public Timestamp getCrtTime() {
        return crtTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.crt_time
     *
     * @param crtTime the value for t_kids.crt_time
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setCrtTime(Timestamp crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.modf_time
     *
     * @return the value of t_kids.modf_time
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public Timestamp getModfTime() {
        return modfTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.modf_time
     *
     * @param modfTime the value for t_kids.modf_time
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setModfTime(Timestamp modfTime) {
        this.modfTime = modfTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.operator
     *
     * @return the value of t_kids.operator
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public Long getOperator() {
        return operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.operator
     *
     * @param operator the value for t_kids.operator
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setOperator(Long operator) {
        this.operator = operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kids.rmk
     *
     * @return the value of t_kids.rmk
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public String getRmk() {
        return rmk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kids.rmk
     *
     * @param rmk the value for t_kids.rmk
     *
     * @mbggenerated Tue Aug 07 23:12:16 CST 2018
     */
    public void setRmk(String rmk) {
        this.rmk = rmk == null ? null : rmk.trim();
    }
}