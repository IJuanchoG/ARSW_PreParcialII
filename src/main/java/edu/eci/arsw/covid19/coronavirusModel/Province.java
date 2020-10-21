package edu.eci.arsw.covid19.coronavirusModel;

import java.util.ArrayList;
import java.util.Date;

public class Province {
    private String name;
    private int confirmed, deaths, recovered;
    private Date lasUpdate;

    public Province(String name ){
        this.name = name;
        this.confirmed = 0;
        this.deaths = 0;
        this.recovered = 0;
        this.lasUpdate = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public Date getLasUpdate() {
        return lasUpdate;
    }

    public void setLasUpdate(Date lasUpdate) {
        this.lasUpdate = lasUpdate;
    }

    @Override
    public String toString() {
        return "Providence{" +
                "name='" + name + '\'' +
                ", confirmed=" + confirmed +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                ", lasUpdate=" + lasUpdate +
                '}';
    }
}
