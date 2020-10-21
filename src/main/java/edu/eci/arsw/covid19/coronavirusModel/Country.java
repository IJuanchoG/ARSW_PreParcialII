package edu.eci.arsw.covid19.coronavirusModel;

import java.util.Date;
import java.util.HashMap;

public class Country {
    private String name;
    private HashMap<String, Province> provinces;
    private int confirmed, deaths, recovered;
    private float lat, lon;
    private Date lasUpdate;

    public Country(String name){
        this.name = name;
        this.confirmed = 0;
        this.deaths = 0;
        this.recovered = 0;
        this.lat = 0;
        this.lon = 0;
        this.provinces = new HashMap<>();
        this.lasUpdate = new Date();

    }

    /**
     * Obtiene la suma de todos los infectados en el pais
     * @return
     */
    public int getAllConfirmedInfected(){
        int sum = 0;
        for(Province p: provinces.values()){
            sum+= p.getConfirmed();
        }
        confirmed = sum;
        return sum;
    }

    /**
     * obtiene la suma de todos los muertos en el pais
     * @return
     */
    public int getAllConfirmedDeaths(){
        int sum = 0;
        for(Province p: provinces.values()){
            sum+= p.getDeaths();
        }
        confirmed = sum;
        return sum;
    }

    /**
     * obtiene los datos de todos los recuperados en el pais
     * @return
     */
    public int getAllConfirmedRecovered(){
        int sum = 0;
        for(Province p: provinces.values()){
            sum+= p.getRecovered();
        }
        confirmed = sum;
        return sum;
    }

    /**
     * Agrega una provincia
     * @param name nombre de la provincia
     * @param province información de la misma
     */
    public void addProvince(String name, Province province){
        provinces.put(name, province);
    }

    /**
     * obtiene todas las provincias del pais
     * @return
     */
    public HashMap<String, Province> getProvinces() {
        return provinces;
    }

    /**
     * Define las provincias del pais
     * @param providences
     */
    public void setProvinces(HashMap<String, Province> providences) {
        this.provinces = providences;
    }


    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) { this.lon = lon; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConfirmed() {
        return getAllConfirmedInfected();
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeaths() {
        return getAllConfirmedDeaths();
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return getAllConfirmedRecovered();
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
        return "Country{" +
                "name='" + name + '\'' +
                ", confirmed=" + confirmed +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                ", lasUpdate=" + lasUpdate +
                '}';
    }
}
