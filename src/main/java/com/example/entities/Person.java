package com.example.entities;

import com.example.annotation.Property;

/**
 * @author : niejun
 * @Description: TODO
 * @date Date : 2022年04月16日 16:32
 **/
public class Person {

    @Property(id = 0)
    public String name;

    @Property(id = 1)
    public Integer age;

    @Property(id = 2)
    public String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
