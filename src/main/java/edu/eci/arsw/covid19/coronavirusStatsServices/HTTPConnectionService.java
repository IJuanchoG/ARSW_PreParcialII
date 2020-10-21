package edu.eci.arsw.covid19.coronavirusStatsServices;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class HTTPConnectionService {

    /**
     * Obtiene la información de todos los paises en rapidAPI
     * @return
     */
    public JSONObject getAllCountries() {
        try {
            HttpResponse<String> response = Unirest.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats")
                    .header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                    .header("x-rapidapi-key", "44dcd8f5d7mshf550bed2a1a379dp1fc49djsnf9da109a1513")
                    .asString();
            return new JSONObject(response.getBody()).getJSONObject("data");
        } catch (UnirestException e) {
            return null;
        }
    }

    /**
     * Obtiene la información específica de un pais en la rapidAPI
     * @param country nombre del pais a consultar
     * @return
     */
    public JSONObject getByCountry(String country) {
        HttpResponse<String> response;
        try {
            response = Unirest.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country="+country)
                    .header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                    .header("x-rapidapi-key", "44dcd8f5d7mshf550bed2a1a379dp1fc49djsnf9da109a1513")
                    .asString();
            return new JSONObject(response.getBody()).getJSONObject("data");
        } catch (UnirestException e) {
            return null;
        }
    }

    /**
     * Obtiene información extra del pais, en este caso su localización
     * @param country nombre del pais a consultar
     * @return
     * @throws UnirestException
     */
    public JSONObject getLocationCountry(String country) throws UnirestException{
        HttpResponse<String> response = Unirest.get("https://restcountries-v1.p.rapidapi.com/name/"+country)
                .header("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "0e72bcd144msh8951afd83016cbbp115df0jsnf8ca2e9da0ec")
                .asString();
        return new JSONObject(new JSONArray(response.getBody()).get(0).toString());
    }


}
