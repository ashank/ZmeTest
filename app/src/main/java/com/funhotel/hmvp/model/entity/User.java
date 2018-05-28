package com.funhotel.hmvp.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：zhiyahan on 2018/3/13 20:37；
 */

@Entity
public class User {

    @Id(autoincrement = true)
    private Long number;

    private String id;
    private String nickName;
    private String realName;
    private int age;
    private int sex;
    private String telePhone;
    private String eamail;
    private String password;
    private String birthday;

    @Generated(hash = 735061427)
    public User(Long number, String id, String nickName, String realName, int age, int sex, String telePhone,
            String eamail, String password, String birthday) {
        this.number = number;
        this.id = id;
        this.nickName = nickName;
        this.realName = realName;
        this.age = age;
        this.sex = sex;
        this.telePhone = telePhone;
        this.eamail = eamail;
        this.password = password;
        this.birthday = birthday;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public String getEamail() {
        return eamail;
    }

    public void setEamail(String eamail) {
        this.eamail = eamail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }


}
