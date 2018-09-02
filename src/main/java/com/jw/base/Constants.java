package com.jw.base;

/**
 * 常量类
 */
public final class Constants {
    public interface KIDS_CLASS_TEACHER_LOG_REL_TYPE_CD{
        byte CLASS_TEACHER_LOG_REL_ASSISTANT = 2;//课程顾问
        byte CLASS_TEACHER_LOG_REL_TEACHER = 1;//带班老师

    }
    public interface KIDS_LOG_OBJ_TYPE_CD{
        byte LOG_OBJ_TYEP_TEACHER = 1;//教师日志
        byte LOG_OBJ_TYPE_KIDS = 2;//学生日志
        byte LOG_OBJ_TYPE_KIDS_READD = 3;//补课日志
        byte LOG_OBJ_TYEP_KIDS_TELE = 4;//电辅日志
    }

    public interface KIDS_IF_APP_ACCOUNT{
        byte YES = 1;//是
        byte NO = 0;//否
    }
}