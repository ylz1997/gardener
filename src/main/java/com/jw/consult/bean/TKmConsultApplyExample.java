package com.jw.consult.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TKmConsultApplyExample {
    //add by jw 增加分页功能
    private int startRow;
    private int pageSize;

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public TKmConsultApplyExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andCnslIdIsNull() {
            addCriterion("CNSL_ID is null");
            return (Criteria) this;
        }

        public Criteria andCnslIdIsNotNull() {
            addCriterion("CNSL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCnslIdEqualTo(Long value) {
            addCriterion("CNSL_ID =", value, "cnslId");
            return (Criteria) this;
        }

        public Criteria andCnslIdNotEqualTo(Long value) {
            addCriterion("CNSL_ID <>", value, "cnslId");
            return (Criteria) this;
        }

        public Criteria andCnslIdGreaterThan(Long value) {
            addCriterion("CNSL_ID >", value, "cnslId");
            return (Criteria) this;
        }

        public Criteria andCnslIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CNSL_ID >=", value, "cnslId");
            return (Criteria) this;
        }

        public Criteria andCnslIdLessThan(Long value) {
            addCriterion("CNSL_ID <", value, "cnslId");
            return (Criteria) this;
        }

        public Criteria andCnslIdLessThanOrEqualTo(Long value) {
            addCriterion("CNSL_ID <=", value, "cnslId");
            return (Criteria) this;
        }

        public Criteria andCnslIdIn(List<Long> values) {
            addCriterion("CNSL_ID in", values, "cnslId");
            return (Criteria) this;
        }

        public Criteria andCnslIdNotIn(List<Long> values) {
            addCriterion("CNSL_ID not in", values, "cnslId");
            return (Criteria) this;
        }

        public Criteria andCnslIdBetween(Long value1, Long value2) {
            addCriterion("CNSL_ID between", value1, value2, "cnslId");
            return (Criteria) this;
        }

        public Criteria andCnslIdNotBetween(Long value1, Long value2) {
            addCriterion("CNSL_ID not between", value1, value2, "cnslId");
            return (Criteria) this;
        }

        public Criteria andCnslNmIsNull() {
            addCriterion("CNSL_NM is null");
            return (Criteria) this;
        }

        public Criteria andCnslNmIsNotNull() {
            addCriterion("CNSL_NM is not null");
            return (Criteria) this;
        }

        public Criteria andCnslNmEqualTo(String value) {
            addCriterion("CNSL_NM =", value, "cnslNm");
            return (Criteria) this;
        }

        public Criteria andCnslNmNotEqualTo(String value) {
            addCriterion("CNSL_NM <>", value, "cnslNm");
            return (Criteria) this;
        }

        public Criteria andCnslNmGreaterThan(String value) {
            addCriterion("CNSL_NM >", value, "cnslNm");
            return (Criteria) this;
        }

        public Criteria andCnslNmGreaterThanOrEqualTo(String value) {
            addCriterion("CNSL_NM >=", value, "cnslNm");
            return (Criteria) this;
        }

        public Criteria andCnslNmLessThan(String value) {
            addCriterion("CNSL_NM <", value, "cnslNm");
            return (Criteria) this;
        }

        public Criteria andCnslNmLessThanOrEqualTo(String value) {
            addCriterion("CNSL_NM <=", value, "cnslNm");
            return (Criteria) this;
        }

        public Criteria andCnslNmLike(String value) {
            addCriterion("CNSL_NM like", value, "cnslNm");
            return (Criteria) this;
        }

        public Criteria andCnslNmNotLike(String value) {
            addCriterion("CNSL_NM not like", value, "cnslNm");
            return (Criteria) this;
        }

        public Criteria andCnslNmIn(List<String> values) {
            addCriterion("CNSL_NM in", values, "cnslNm");
            return (Criteria) this;
        }

        public Criteria andCnslNmNotIn(List<String> values) {
            addCriterion("CNSL_NM not in", values, "cnslNm");
            return (Criteria) this;
        }

        public Criteria andCnslNmBetween(String value1, String value2) {
            addCriterion("CNSL_NM between", value1, value2, "cnslNm");
            return (Criteria) this;
        }

        public Criteria andCnslNmNotBetween(String value1, String value2) {
            addCriterion("CNSL_NM not between", value1, value2, "cnslNm");
            return (Criteria) this;
        }

        public Criteria andTmpltIdIsNull() {
            addCriterion("TMPLT_ID is null");
            return (Criteria) this;
        }

        public Criteria andTmpltIdIsNotNull() {
            addCriterion("TMPLT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTmpltIdEqualTo(Long value) {
            addCriterion("TMPLT_ID =", value, "tmpltId");
            return (Criteria) this;
        }

        public Criteria andTmpltIdNotEqualTo(Long value) {
            addCriterion("TMPLT_ID <>", value, "tmpltId");
            return (Criteria) this;
        }

        public Criteria andTmpltIdGreaterThan(Long value) {
            addCriterion("TMPLT_ID >", value, "tmpltId");
            return (Criteria) this;
        }

        public Criteria andTmpltIdGreaterThanOrEqualTo(Long value) {
            addCriterion("TMPLT_ID >=", value, "tmpltId");
            return (Criteria) this;
        }

        public Criteria andTmpltIdLessThan(Long value) {
            addCriterion("TMPLT_ID <", value, "tmpltId");
            return (Criteria) this;
        }

        public Criteria andTmpltIdLessThanOrEqualTo(Long value) {
            addCriterion("TMPLT_ID <=", value, "tmpltId");
            return (Criteria) this;
        }

        public Criteria andTmpltIdIn(List<Long> values) {
            addCriterion("TMPLT_ID in", values, "tmpltId");
            return (Criteria) this;
        }

        public Criteria andTmpltIdNotIn(List<Long> values) {
            addCriterion("TMPLT_ID not in", values, "tmpltId");
            return (Criteria) this;
        }

        public Criteria andTmpltIdBetween(Long value1, Long value2) {
            addCriterion("TMPLT_ID between", value1, value2, "tmpltId");
            return (Criteria) this;
        }

        public Criteria andTmpltIdNotBetween(Long value1, Long value2) {
            addCriterion("TMPLT_ID not between", value1, value2, "tmpltId");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoIsNull() {
            addCriterion("ARGE_SEQNO is null");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoIsNotNull() {
            addCriterion("ARGE_SEQNO is not null");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoEqualTo(Short value) {
            addCriterion("ARGE_SEQNO =", value, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoNotEqualTo(Short value) {
            addCriterion("ARGE_SEQNO <>", value, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoGreaterThan(Short value) {
            addCriterion("ARGE_SEQNO >", value, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoGreaterThanOrEqualTo(Short value) {
            addCriterion("ARGE_SEQNO >=", value, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoLessThan(Short value) {
            addCriterion("ARGE_SEQNO <", value, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoLessThanOrEqualTo(Short value) {
            addCriterion("ARGE_SEQNO <=", value, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoIn(List<Short> values) {
            addCriterion("ARGE_SEQNO in", values, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoNotIn(List<Short> values) {
            addCriterion("ARGE_SEQNO not in", values, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoBetween(Short value1, Short value2) {
            addCriterion("ARGE_SEQNO between", value1, value2, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andArgeSeqnoNotBetween(Short value1, Short value2) {
            addCriterion("ARGE_SEQNO not between", value1, value2, "argeSeqno");
            return (Criteria) this;
        }

        public Criteria andRmkIsNull() {
            addCriterion("RMK is null");
            return (Criteria) this;
        }

        public Criteria andRmkIsNotNull() {
            addCriterion("RMK is not null");
            return (Criteria) this;
        }

        public Criteria andRmkEqualTo(String value) {
            addCriterion("RMK =", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkNotEqualTo(String value) {
            addCriterion("RMK <>", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkGreaterThan(String value) {
            addCriterion("RMK >", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkGreaterThanOrEqualTo(String value) {
            addCriterion("RMK >=", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkLessThan(String value) {
            addCriterion("RMK <", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkLessThanOrEqualTo(String value) {
            addCriterion("RMK <=", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkLike(String value) {
            addCriterion("RMK like", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkNotLike(String value) {
            addCriterion("RMK not like", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkIn(List<String> values) {
            addCriterion("RMK in", values, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkNotIn(List<String> values) {
            addCriterion("RMK not in", values, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkBetween(String value1, String value2) {
            addCriterion("RMK between", value1, value2, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkNotBetween(String value1, String value2) {
            addCriterion("RMK not between", value1, value2, "rmk");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdIsNull() {
            addCriterion("OP_PRSN_ID is null");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdIsNotNull() {
            addCriterion("OP_PRSN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdEqualTo(String value) {
            addCriterion("OP_PRSN_ID =", value, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdNotEqualTo(String value) {
            addCriterion("OP_PRSN_ID <>", value, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdGreaterThan(String value) {
            addCriterion("OP_PRSN_ID >", value, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdGreaterThanOrEqualTo(String value) {
            addCriterion("OP_PRSN_ID >=", value, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdLessThan(String value) {
            addCriterion("OP_PRSN_ID <", value, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdLessThanOrEqualTo(String value) {
            addCriterion("OP_PRSN_ID <=", value, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdLike(String value) {
            addCriterion("OP_PRSN_ID like", value, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdNotLike(String value) {
            addCriterion("OP_PRSN_ID not like", value, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdIn(List<String> values) {
            addCriterion("OP_PRSN_ID in", values, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdNotIn(List<String> values) {
            addCriterion("OP_PRSN_ID not in", values, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdBetween(String value1, String value2) {
            addCriterion("OP_PRSN_ID between", value1, value2, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andOpPrsnIdNotBetween(String value1, String value2) {
            addCriterion("OP_PRSN_ID not between", value1, value2, "opPrsnId");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIsNull() {
            addCriterion("CRT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIsNotNull() {
            addCriterion("CRT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCrtTimeEqualTo(Timestamp value) {
            addCriterion("CRT_TIME =", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotEqualTo(Timestamp value) {
            addCriterion("CRT_TIME <>", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeGreaterThan(Timestamp value) {
            addCriterion("CRT_TIME >", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CRT_TIME >=", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeLessThan(Timestamp value) {
            addCriterion("CRT_TIME <", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("CRT_TIME <=", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIn(List<Timestamp> values) {
            addCriterion("CRT_TIME in", values, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotIn(List<Timestamp> values) {
            addCriterion("CRT_TIME not in", values, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CRT_TIME between", value1, value2, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CRT_TIME not between", value1, value2, "crtTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeIsNull() {
            addCriterion("MODF_TIME is null");
            return (Criteria) this;
        }

        public Criteria andModfTimeIsNotNull() {
            addCriterion("MODF_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andModfTimeEqualTo(Timestamp value) {
            addCriterion("MODF_TIME =", value, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeNotEqualTo(Timestamp value) {
            addCriterion("MODF_TIME <>", value, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeGreaterThan(Timestamp value) {
            addCriterion("MODF_TIME >", value, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("MODF_TIME >=", value, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeLessThan(Timestamp value) {
            addCriterion("MODF_TIME <", value, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("MODF_TIME <=", value, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeIn(List<Timestamp> values) {
            addCriterion("MODF_TIME in", values, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeNotIn(List<Timestamp> values) {
            addCriterion("MODF_TIME not in", values, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("MODF_TIME between", value1, value2, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("MODF_TIME not between", value1, value2, "modfTime");
            return (Criteria) this;
        }

        public Criteria andRegnIdIsNull() {
            addCriterion("REGN_ID is null");
            return (Criteria) this;
        }

        public Criteria andRegnIdIsNotNull() {
            addCriterion("REGN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRegnIdEqualTo(String value) {
            addCriterion("REGN_ID =", value, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdNotEqualTo(String value) {
            addCriterion("REGN_ID <>", value, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdGreaterThan(String value) {
            addCriterion("REGN_ID >", value, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdGreaterThanOrEqualTo(String value) {
            addCriterion("REGN_ID >=", value, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdLessThan(String value) {
            addCriterion("REGN_ID <", value, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdLessThanOrEqualTo(String value) {
            addCriterion("REGN_ID <=", value, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdLike(String value) {
            addCriterion("REGN_ID like", value, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdNotLike(String value) {
            addCriterion("REGN_ID not like", value, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdIn(List<String> values) {
            addCriterion("REGN_ID in", values, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdNotIn(List<String> values) {
            addCriterion("REGN_ID not in", values, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdBetween(String value1, String value2) {
            addCriterion("REGN_ID between", value1, value2, "regnId");
            return (Criteria) this;
        }

        public Criteria andRegnIdNotBetween(String value1, String value2) {
            addCriterion("REGN_ID not between", value1, value2, "regnId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNull() {
            addCriterion("TENANT_ID is null");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNotNull() {
            addCriterion("TENANT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTenantIdEqualTo(String value) {
            addCriterion("TENANT_ID =", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotEqualTo(String value) {
            addCriterion("TENANT_ID <>", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThan(String value) {
            addCriterion("TENANT_ID >", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThanOrEqualTo(String value) {
            addCriterion("TENANT_ID >=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThan(String value) {
            addCriterion("TENANT_ID <", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThanOrEqualTo(String value) {
            addCriterion("TENANT_ID <=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLike(String value) {
            addCriterion("TENANT_ID like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotLike(String value) {
            addCriterion("TENANT_ID not like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIn(List<String> values) {
            addCriterion("TENANT_ID in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotIn(List<String> values) {
            addCriterion("TENANT_ID not in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdBetween(String value1, String value2) {
            addCriterion("TENANT_ID between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotBetween(String value1, String value2) {
            addCriterion("TENANT_ID not between", value1, value2, "tenantId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated do_not_delete_during_merge Tue Feb 27 15:43:36 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_km_consult_apply
     *
     * @mbggenerated Tue Feb 27 15:43:36 CST 2018
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}