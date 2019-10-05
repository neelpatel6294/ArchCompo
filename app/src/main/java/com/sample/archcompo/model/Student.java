package com.sample.archcompo.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_Db")
public class Student implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int studentId;
    private String name;
    private String email;
    private String country;
    private String regTime;

    @Ignore
    public Student() {
    }

    public Student(int studentId, String name, String email, String country) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.country = country;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() { return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.studentId);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.country);
        dest.writeString(this.regTime);
    }

    protected Student(Parcel in) {
        this.studentId = in.readInt();
        this.name = in.readString();
        this.email = in.readString();
        this.country = in.readString();
        this.regTime = in.readString();
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
