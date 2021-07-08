package com.hazards.bean;

import java.util.Date;

public class Student {
    private Integer sid;
    private String sname;
    private String gender;
    private Date sbir;
    private String hobby;
    private String photo;

    public Student(){}

    public Student(String sname, String gender, Date sbir, String hobby, String photo) {
        this.sname = sname;
        this.gender = gender;
        this.sbir = sbir;
        this.hobby = hobby;
        this.photo = photo;
    }

    public Student(Integer sid, String sname, String gender, Date birthday, String hobby, String photo) {
        this.sid = sid;
        this.sname = sname;
        this.gender = gender;
        this.sbir = birthday;
        this.hobby = hobby;
        this.photo = photo;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public Date getSbir() {
        return sbir;
    }

    public void setSbir(Date sbir) {
        this.sbir = sbir;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", gender='" + gender + '\'' +
                ", sbir=" + sbir +
                ", hobby='" + hobby + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
