package com.example.android_lab4;

import java.io.Serializable;

public class Animal implements Serializable {

    private int _id;

    private String gatunek;

    private String kolor;

    private float wielkosc;

    private String opis;

    public Animal() {}

    public Animal(String gatunek, String kolor, float wielkosc, String opis) {
        this.gatunek = gatunek;
        this.kolor = kolor;
        this.wielkosc = wielkosc;
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "com.example.android_lab4.Animal{" +
                "_id=" + _id +
                ", gatunek='" + gatunek + '\'' +
                ", kolor='" + kolor + '\'' +
                ", wielkosc=" + wielkosc +
                ", opis='" + opis + '\'' +
                '}';
    }

    public int get_id() {
        return _id;
    }

    public String getGatunek() {
        return gatunek;
    }

    public String getKolor() {
        return kolor;
    }

    public float getWielkosc() {
        return wielkosc;
    }

    public String getOpis() {
        return opis;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }

    public void setKolor(String kolor) {
        this.kolor = kolor;
    }

    public void setWielkosc(float wielkosc) {
        this.wielkosc = wielkosc;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
