package com.ra.lecture.md04ss09.model.entity;

import java.sql.Date;

public class Student {
    private int id;
    private String name;
    private Date birthday;
    private int classId;

    public Student() {
    }

    public Student(int id, String name, Date birthday, int classId) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.classId = classId;
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
}
