package com.example.powerslave.person;

import android.net.Uri;

import com.example.powerslave.government.Continent;
import com.example.powerslave.government.Country;
import com.example.powerslave.government.Ideology;

public abstract class Person implements Comparable<Person>{
    protected String name;
    protected String surname;
    protected Sex sex;
    protected Country country;
    protected Ideology ideology;
    protected Uri portrait;

    public Person(String name, String surname, Sex sex, Country country, Ideology ideology, Uri portrait) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.country = country;
        this.ideology = ideology;
        this.portrait = portrait;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Uri getPortrait() {
        return portrait;
    }

    public void setPortrait(Uri portrait) {
        this.portrait = portrait;
    }

    @Override
    public String toString() {
        return name + '\n' + surname + '\n' +
                "S: " + sex + '\n' +
                "I: " + ideology;
    }

    @Override
    public int compareTo(Person p) {
        return this.name.compareTo(p.name);
    }
}
