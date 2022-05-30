package com.example.powerslave.person;

import com.example.powerslave.government.Continent;
import com.example.powerslave.government.Ideology;

public abstract class Person {
    private String name;
    private String surname;
    private Sex sex;
    private Ideology ideology;

    public Person(String name, String surname, Sex sex, Continent motherland, Ideology ideology) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.ideology = ideology;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Ideology getIdeology() {
        return ideology;
    }

    public void setIdeology(Ideology ideology) {
        this.ideology = ideology;
    }

    @Override
    public String toString() {
        return "Name: " + name + '\n' +
                        "Surname: " + surname + '\n' +
                        "Sex: " + sex + '\n' +
                        "Ideology: " + ideology;
    }
}
