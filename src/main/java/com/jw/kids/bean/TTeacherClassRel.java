package com.jw.kids.bean;

import java.util.Date;

public class TTeacherClassRel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_teacher_class_rel.rl_id
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    private Long rlId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_teacher_class_rel.class_id
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    private Long classId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_teacher_class_rel.teacher_id
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    private Long teacherId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_teacher_class_rel.rl_type
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    private Byte rlType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_teacher_class_rel.crt_time
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    private Date crtTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_teacher_class_rel.modf_time
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    private Date modfTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_teacher_class_rel.operator
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    private Long operator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_teacher_class_rel.rmk
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    private String rmk;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher_class_rel
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    public TTeacherClassRel(Long rlId, Long classId, Long teacherId, Byte rlType, Date crtTime, Date modfTime, Long operator, String rmk) {
        this.rlId = rlId;
        this.classId = classId;
        this.teacherId = teacherId;
        this.rlType = rlType;
        this.crtTime = crtTime;
        this.modfTime = modfTime;
        this.operator = operator;
        this.rmk = rmk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher_class_rel
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    public TTeacherClassRel() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_teacher_class_rel.rl_id
     *
     * @return the value of t_teacher_class_rel.rl_id
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    public Long getRlId() {
        return rlId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_teacher_class_rel.rl_id
     *
     * @param rlId the value for t_teacher_class_rel.rl_id
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    public void setRlId(Long rlId) {
        this.rlId = rlId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_teacher_class_rel.class_id
     *
     * @return the value of t_teacher_class_rel.class_id
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    public Long getClassId() {
        return classId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_teacher_class_rel.class_id
     *
     * @param classId the value for t_teacher_class_rel.class_id
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_teacher_class_rel.teacher_id
     *
     * @return the value of t_teacher_class_rel.teacher_id
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    public Long getTeacherId() {
        return teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_teacher_class_rel.teacher_id
     *
     * @param teacherId the value for t_teacher_class_rel.teacher_id
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_teacher_class_rel.rl_type
     *
     * @return the value of t_teacher_class_rel.rl_type
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    public Byte getRlType() {
        return rlType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_teacher_class_rel.rl_type
     *
     * @param rlType the value for t_teacher_class_rel.rl_type
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    public void setRlType(Byte rlType) {
        this.rlType = rlType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_teacher_class_rel.crt_time
     *
     * @return the value of t_teacher_class_rel.crt_time
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    public Date getCrtTime() {
        return crtTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_teacher_class_rel.crt_time
     *
     * @param crtTime the value for t_teacher_class_rel.crt_time
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_teacher_class_rel.modf_time
     *
     * @return the value of t_teacher_class_rel.modf_time
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    public Date getModfTime() {
        return modfTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_teacher_class_rel.modf_time
     *
     * @param modfTime the value for t_teacher_class_rel.modf_time
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    public void setModfTime(Date modfTime) {
        this.modfTime = modfTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_teacher_class_rel.operator
     *
     * @return the value of t_teacher_class_rel.operator
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    public Long getOperator() {
        return operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_teacher_class_rel.operator
     *
     * @param operator the value for t_teacher_class_rel.operator
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    public void setOperator(Long operator) {
        this.operator = operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_teacher_class_rel.rmk
     *
     * @return the value of t_teacher_class_rel.rmk
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    public String getRmk() {
        return rmk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_teacher_class_rel.rmk
     *
     * @param rmk the value for t_teacher_class_rel.rmk
     *
     * @mbggenerated Sun Jun 24 22:49:08 CST 2018
     */
    public void setRmk(String rmk) {
        this.rmk = rmk == null ? null : rmk.trim();
    }
}