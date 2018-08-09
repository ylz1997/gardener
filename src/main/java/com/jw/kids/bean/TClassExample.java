package com.jw.kids.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TClassExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_class
     *
     * @mbggenerated Thu Aug 09 23:04:15 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_class
     *
     * @mbggenerated Thu Aug 09 23:04:15 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_class
     *
     * @mbggenerated Thu Aug 09 23:04:15 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class
     *
     * @mbggenerated Thu Aug 09 23:04:15 CST 2018
     */
    public TClassExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class
     *
     * @mbggenerated Thu Aug 09 23:04:15 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class
     *
     * @mbggenerated Thu Aug 09 23:04:15 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class
     *
     * @mbggenerated Thu Aug 09 23:04:15 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class
     *
     * @mbggenerated Thu Aug 09 23:04:15 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class
     *
     * @mbggenerated Thu Aug 09 23:04:15 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class
     *
     * @mbggenerated Thu Aug 09 23:04:15 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class
     *
     * @mbggenerated Thu Aug 09 23:04:15 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class
     *
     * @mbggenerated Thu Aug 09 23:04:15 CST 2018
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
     * This method corresponds to the database table t_class
     *
     * @mbggenerated Thu Aug 09 23:04:15 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class
     *
     * @mbggenerated Thu Aug 09 23:04:15 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_class
     *
     * @mbggenerated Thu Aug 09 23:04:15 CST 2018
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

        public Criteria andClassIdIsNull() {
            addCriterion("class_id is null");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNotNull() {
            addCriterion("class_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassIdEqualTo(Long value) {
            addCriterion("class_id =", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotEqualTo(Long value) {
            addCriterion("class_id <>", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThan(Long value) {
            addCriterion("class_id >", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThanOrEqualTo(Long value) {
            addCriterion("class_id >=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThan(Long value) {
            addCriterion("class_id <", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThanOrEqualTo(Long value) {
            addCriterion("class_id <=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdIn(List<Long> values) {
            addCriterion("class_id in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotIn(List<Long> values) {
            addCriterion("class_id not in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdBetween(Long value1, Long value2) {
            addCriterion("class_id between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotBetween(Long value1, Long value2) {
            addCriterion("class_id not between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassNmIsNull() {
            addCriterion("class_nm is null");
            return (Criteria) this;
        }

        public Criteria andClassNmIsNotNull() {
            addCriterion("class_nm is not null");
            return (Criteria) this;
        }

        public Criteria andClassNmEqualTo(String value) {
            addCriterion("class_nm =", value, "classNm");
            return (Criteria) this;
        }

        public Criteria andClassNmNotEqualTo(String value) {
            addCriterion("class_nm <>", value, "classNm");
            return (Criteria) this;
        }

        public Criteria andClassNmGreaterThan(String value) {
            addCriterion("class_nm >", value, "classNm");
            return (Criteria) this;
        }

        public Criteria andClassNmGreaterThanOrEqualTo(String value) {
            addCriterion("class_nm >=", value, "classNm");
            return (Criteria) this;
        }

        public Criteria andClassNmLessThan(String value) {
            addCriterion("class_nm <", value, "classNm");
            return (Criteria) this;
        }

        public Criteria andClassNmLessThanOrEqualTo(String value) {
            addCriterion("class_nm <=", value, "classNm");
            return (Criteria) this;
        }

        public Criteria andClassNmLike(String value) {
            addCriterion("class_nm like", value, "classNm");
            return (Criteria) this;
        }

        public Criteria andClassNmNotLike(String value) {
            addCriterion("class_nm not like", value, "classNm");
            return (Criteria) this;
        }

        public Criteria andClassNmIn(List<String> values) {
            addCriterion("class_nm in", values, "classNm");
            return (Criteria) this;
        }

        public Criteria andClassNmNotIn(List<String> values) {
            addCriterion("class_nm not in", values, "classNm");
            return (Criteria) this;
        }

        public Criteria andClassNmBetween(String value1, String value2) {
            addCriterion("class_nm between", value1, value2, "classNm");
            return (Criteria) this;
        }

        public Criteria andClassNmNotBetween(String value1, String value2) {
            addCriterion("class_nm not between", value1, value2, "classNm");
            return (Criteria) this;
        }

        public Criteria andCycleIsNull() {
            addCriterion("cycle is null");
            return (Criteria) this;
        }

        public Criteria andCycleIsNotNull() {
            addCriterion("cycle is not null");
            return (Criteria) this;
        }

        public Criteria andCycleEqualTo(String value) {
            addCriterion("cycle =", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotEqualTo(String value) {
            addCriterion("cycle <>", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleGreaterThan(String value) {
            addCriterion("cycle >", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleGreaterThanOrEqualTo(String value) {
            addCriterion("cycle >=", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleLessThan(String value) {
            addCriterion("cycle <", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleLessThanOrEqualTo(String value) {
            addCriterion("cycle <=", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleLike(String value) {
            addCriterion("cycle like", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotLike(String value) {
            addCriterion("cycle not like", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleIn(List<String> values) {
            addCriterion("cycle in", values, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotIn(List<String> values) {
            addCriterion("cycle not in", values, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleBetween(String value1, String value2) {
            addCriterion("cycle between", value1, value2, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotBetween(String value1, String value2) {
            addCriterion("cycle not between", value1, value2, "cycle");
            return (Criteria) this;
        }

        public Criteria andClassPackageIdIsNull() {
            addCriterion("class_package_id is null");
            return (Criteria) this;
        }

        public Criteria andClassPackageIdIsNotNull() {
            addCriterion("class_package_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassPackageIdEqualTo(Long value) {
            addCriterion("class_package_id =", value, "classPackageId");
            return (Criteria) this;
        }

        public Criteria andClassPackageIdNotEqualTo(Long value) {
            addCriterion("class_package_id <>", value, "classPackageId");
            return (Criteria) this;
        }

        public Criteria andClassPackageIdGreaterThan(Long value) {
            addCriterion("class_package_id >", value, "classPackageId");
            return (Criteria) this;
        }

        public Criteria andClassPackageIdGreaterThanOrEqualTo(Long value) {
            addCriterion("class_package_id >=", value, "classPackageId");
            return (Criteria) this;
        }

        public Criteria andClassPackageIdLessThan(Long value) {
            addCriterion("class_package_id <", value, "classPackageId");
            return (Criteria) this;
        }

        public Criteria andClassPackageIdLessThanOrEqualTo(Long value) {
            addCriterion("class_package_id <=", value, "classPackageId");
            return (Criteria) this;
        }

        public Criteria andClassPackageIdIn(List<Long> values) {
            addCriterion("class_package_id in", values, "classPackageId");
            return (Criteria) this;
        }

        public Criteria andClassPackageIdNotIn(List<Long> values) {
            addCriterion("class_package_id not in", values, "classPackageId");
            return (Criteria) this;
        }

        public Criteria andClassPackageIdBetween(Long value1, Long value2) {
            addCriterion("class_package_id between", value1, value2, "classPackageId");
            return (Criteria) this;
        }

        public Criteria andClassPackageIdNotBetween(Long value1, Long value2) {
            addCriterion("class_package_id not between", value1, value2, "classPackageId");
            return (Criteria) this;
        }

        public Criteria andClassTimeIsNull() {
            addCriterion("class_time is null");
            return (Criteria) this;
        }

        public Criteria andClassTimeIsNotNull() {
            addCriterion("class_time is not null");
            return (Criteria) this;
        }

        public Criteria andClassTimeEqualTo(String value) {
            addCriterion("class_time =", value, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeNotEqualTo(String value) {
            addCriterion("class_time <>", value, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeGreaterThan(String value) {
            addCriterion("class_time >", value, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeGreaterThanOrEqualTo(String value) {
            addCriterion("class_time >=", value, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeLessThan(String value) {
            addCriterion("class_time <", value, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeLessThanOrEqualTo(String value) {
            addCriterion("class_time <=", value, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeLike(String value) {
            addCriterion("class_time like", value, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeNotLike(String value) {
            addCriterion("class_time not like", value, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeIn(List<String> values) {
            addCriterion("class_time in", values, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeNotIn(List<String> values) {
            addCriterion("class_time not in", values, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeBetween(String value1, String value2) {
            addCriterion("class_time between", value1, value2, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeNotBetween(String value1, String value2) {
            addCriterion("class_time not between", value1, value2, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassLevelIsNull() {
            addCriterion("class_level is null");
            return (Criteria) this;
        }

        public Criteria andClassLevelIsNotNull() {
            addCriterion("class_level is not null");
            return (Criteria) this;
        }

        public Criteria andClassLevelEqualTo(Byte value) {
            addCriterion("class_level =", value, "classLevel");
            return (Criteria) this;
        }

        public Criteria andClassLevelNotEqualTo(Byte value) {
            addCriterion("class_level <>", value, "classLevel");
            return (Criteria) this;
        }

        public Criteria andClassLevelGreaterThan(Byte value) {
            addCriterion("class_level >", value, "classLevel");
            return (Criteria) this;
        }

        public Criteria andClassLevelGreaterThanOrEqualTo(Byte value) {
            addCriterion("class_level >=", value, "classLevel");
            return (Criteria) this;
        }

        public Criteria andClassLevelLessThan(Byte value) {
            addCriterion("class_level <", value, "classLevel");
            return (Criteria) this;
        }

        public Criteria andClassLevelLessThanOrEqualTo(Byte value) {
            addCriterion("class_level <=", value, "classLevel");
            return (Criteria) this;
        }

        public Criteria andClassLevelIn(List<Byte> values) {
            addCriterion("class_level in", values, "classLevel");
            return (Criteria) this;
        }

        public Criteria andClassLevelNotIn(List<Byte> values) {
            addCriterion("class_level not in", values, "classLevel");
            return (Criteria) this;
        }

        public Criteria andClassLevelBetween(Byte value1, Byte value2) {
            addCriterion("class_level between", value1, value2, "classLevel");
            return (Criteria) this;
        }

        public Criteria andClassLevelNotBetween(Byte value1, Byte value2) {
            addCriterion("class_level not between", value1, value2, "classLevel");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("begin_time is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Timestamp value) {
            addCriterion("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Timestamp value) {
            addCriterion("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Timestamp value) {
            addCriterion("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Timestamp value) {
            addCriterion("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Timestamp> values) {
            addCriterion("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Timestamp> values) {
            addCriterion("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("begin_time not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Timestamp value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Timestamp value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Timestamp value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Timestamp value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Timestamp> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Timestamp> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIsNull() {
            addCriterion("crt_time is null");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIsNotNull() {
            addCriterion("crt_time is not null");
            return (Criteria) this;
        }

        public Criteria andCrtTimeEqualTo(Timestamp value) {
            addCriterion("crt_time =", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotEqualTo(Timestamp value) {
            addCriterion("crt_time <>", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeGreaterThan(Timestamp value) {
            addCriterion("crt_time >", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("crt_time >=", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeLessThan(Timestamp value) {
            addCriterion("crt_time <", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("crt_time <=", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIn(List<Timestamp> values) {
            addCriterion("crt_time in", values, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotIn(List<Timestamp> values) {
            addCriterion("crt_time not in", values, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("crt_time between", value1, value2, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("crt_time not between", value1, value2, "crtTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeIsNull() {
            addCriterion("modf_time is null");
            return (Criteria) this;
        }

        public Criteria andModfTimeIsNotNull() {
            addCriterion("modf_time is not null");
            return (Criteria) this;
        }

        public Criteria andModfTimeEqualTo(Timestamp value) {
            addCriterion("modf_time =", value, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeNotEqualTo(Timestamp value) {
            addCriterion("modf_time <>", value, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeGreaterThan(Timestamp value) {
            addCriterion("modf_time >", value, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("modf_time >=", value, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeLessThan(Timestamp value) {
            addCriterion("modf_time <", value, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("modf_time <=", value, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeIn(List<Timestamp> values) {
            addCriterion("modf_time in", values, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeNotIn(List<Timestamp> values) {
            addCriterion("modf_time not in", values, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("modf_time between", value1, value2, "modfTime");
            return (Criteria) this;
        }

        public Criteria andModfTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("modf_time not between", value1, value2, "modfTime");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(Long value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(Long value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(Long value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(Long value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(Long value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(Long value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<Long> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<Long> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(Long value1, Long value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(Long value1, Long value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andRmkIsNull() {
            addCriterion("rmk is null");
            return (Criteria) this;
        }

        public Criteria andRmkIsNotNull() {
            addCriterion("rmk is not null");
            return (Criteria) this;
        }

        public Criteria andRmkEqualTo(String value) {
            addCriterion("rmk =", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkNotEqualTo(String value) {
            addCriterion("rmk <>", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkGreaterThan(String value) {
            addCriterion("rmk >", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkGreaterThanOrEqualTo(String value) {
            addCriterion("rmk >=", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkLessThan(String value) {
            addCriterion("rmk <", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkLessThanOrEqualTo(String value) {
            addCriterion("rmk <=", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkLike(String value) {
            addCriterion("rmk like", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkNotLike(String value) {
            addCriterion("rmk not like", value, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkIn(List<String> values) {
            addCriterion("rmk in", values, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkNotIn(List<String> values) {
            addCriterion("rmk not in", values, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkBetween(String value1, String value2) {
            addCriterion("rmk between", value1, value2, "rmk");
            return (Criteria) this;
        }

        public Criteria andRmkNotBetween(String value1, String value2) {
            addCriterion("rmk not between", value1, value2, "rmk");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_class
     *
     * @mbggenerated do_not_delete_during_merge Thu Aug 09 23:04:15 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_class
     *
     * @mbggenerated Thu Aug 09 23:04:15 CST 2018
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