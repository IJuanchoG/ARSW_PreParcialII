package edu.eci.arsw.covid19.coronavirusStatsController;

import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.covid19.coronavirusModel.Country;
import edu.eci.arsw.covid19.coronavirusStatsServices.CoronavirusStatsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/covid19")
public class CoronavirusController {

    @Autowired
    private CoronavirusStatsServices cvss;

    /**
     *  Obtiene todos los paises
     * @return
     */
    @RequestMapping(value="/AllCountries",method= RequestMethod.GET)
    public ResponseEntity<?> getAllCases(){
        return new ResponseEntity<>(cvss.getAll(), HttpStatus.ACCEPTED);
    }

    /**
     * obtiene información de un pais en específico
     * @param country nombre del pais a consultar
     * @return
     */
    @RequestMapping(value="/country/{country}")
    public ResponseEntity<?> getCasesByCountry(@PathVariable String country) {
        Country stats = null;
        try {
            stats = cvss.getByCountry(country);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        if(cvss!=null) {
            return new ResponseEntity<>(stats, HttpStatus.OK);
        }
        return new ResponseEntity<>("El pais no existe", HttpStatus.NOT_FOUND);

    }


}
