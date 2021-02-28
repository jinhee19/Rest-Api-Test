package com.najinji.domain.userInfo;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "user_info")
@Entity
public class UserInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String usr_name;
    @Id
    private String email;
    private String sex;
    private int age;
    private String residence;

    public UserInfo(){}

    public UserInfo(String usr_name, String email, String sex, int age, String residence) {
        this.usr_name = usr_name;
        this.email = email;
        this.sex = sex;
        this.age = age;
        this.residence = residence;
    }

    public UserInfo(String usr_name, String email) {
        this.usr_name = usr_name;
        this.email = email;
    }

    public String getUsr_name() {
        return usr_name;
    }

    public String getEmail() {
        return email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex){this.sex = sex;}

    public int getAge() {
        return age;
    }

    public void setAge(int age){this.age = age;}

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {this.residence = residence;}
}
