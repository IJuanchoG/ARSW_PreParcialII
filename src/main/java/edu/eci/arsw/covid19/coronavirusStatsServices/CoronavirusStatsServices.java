package edu.eci.arsw.covid19.coronavirusStatsServices;

import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.covid19.coronavirusModel.Country;
import edu.eci.arsw.covid19.coronavirusModel.Province;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class CoronavirusStatsServices {
    @Autowired
    HTTPConnectionService hcs;

    /**
     * Esta funcion encuentra una provincia según su nombre y pais
     * @param name nombre de la provincia
     * @param Country pais al que pertenece
     * @return provincia
     */
    public Province getByProvidenceAndCountry(String name, String Country) {
        try {
            getByCountry(Country).getProvinces().get(name);
        }catch (Exception e){

        }
        return null;
    }

    /**
     * obtiene todos los paises registrados
     * @return
     */
    public ArrayList<Country> getAll() {

        JSONObject obj = hcs.getAllCountries();
        JSONArray jsonarray = obj.getJSONArray("covid19Stats");
        HashMap<String, Country> countryHashMap = new HashMap<>();

        for (Object j: jsonarray){
            JSONObject tmp = (JSONObject)j;
            Country country =  null;
            if(!countryHashMap.containsKey(tmp.getString("country"))){
                country = new Country(tmp.getString("country"));
                countryHashMap.put(tmp.getString("country"),country);

            }else{
                country = countryHashMap.get(tmp.getString("country"));
            }

            if(!country.getProvinces().containsKey(tmp.getString("province"))) {

                country.addProvince(tmp.getString("province"), newProvince(tmp));
            }

        }
        ArrayList<Country> countriesGet = new ArrayList<>();
        for(Country c: countryHashMap.values()) countriesGet.add(c);
        return countriesGet;
    }


    /**
     * Obtiene la información de un pais
     * @param country nombre del pais
     * @return
     * @throws UnirestException
     */
    public Country getByCountry(String country) throws UnirestException {
        ArrayList<Country> countries= getAll();
        for(Country c: countries) {
            if (c.getName().equals(country)){
                JSONArray locationCountry = new JSONArray(hcs.getLocationCountry(country).get("latlng").toString());
                c.setLat(locationCountry.getLong(0));
                c.setLon(locationCountry.getLong(1));
                return c;
            }
        }

        return new Country("No se encuentran registros del país mencionado") ;
    }

    /**
     * Crea una nueva provincia
     * @param tmp JSONObject con la información
     * @return
     */
    private Province newProvince(JSONObject tmp) {
        Province province = new Province(tmp.getString("province"));
        province.setConfirmed(tmp.getInt("confirmed"));
        province.setDeaths(tmp.getInt("deaths"));
        province.setRecovered(tmp.getInt("recovered"));
        return province;
    }

}
