package com.ra.lecture.md04ss09.dto;

import java.sql.Date;

public class StudentDTO {
    private int id;
    private String name;
    private Date birthday;
    private int classId;
    private String className;

    public StudentDTO() {
    }

    public StudentDTO(int id, String name, Date birthday, int classId, String className) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.classId = classId;
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
