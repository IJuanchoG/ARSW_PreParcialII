var api = apiclient;
var Module = (function () {

    function _map(list) {
        return mapList = list.map(function (country) {
            return {name: country.name,
                provinces: country.provinces,
                confirmed: country.confirmed,
                deaths: country.deaths,
                recovered: country.recovered,
                lat: country.lat,
                lon: country.lon
            };
        })
    }

    function _table(result) {
        countries = _map(result);
        $("#status > tbody").empty();
        countries.map(function (country) {
            $("#status > tbody").append(
                "<tr> <td>" +
                "<form><button class='btn btn-primary' type='button' onclick='Module.getCasesByName(\"" +
            country.name+ "\""+ ")' >"+country.name+"</button></form>"+
                "</td>" +
                "<td>" +
                country.deaths +
                "</td> " +
                "<td>" +
                country.confirmed+
                "</td> " +
                "<td>" +
                country.recovered+"</td>" +
                "</tr>"
            );
        });
    };
    function _tabled(country) {
        document.getElementById("countryname").innerHTML = country.name;
        $("#country > tbody").empty();

        $("#country > tbody").append(
            "<tr> <td> Num Deaths</td> <td>" +
            country.deaths +
            "</td></tr>" +
            "<tr> <td> Num Confirmed</td> <td>" +
            country.confirmed +
            "</td></tr>"+
        "<tr> <td> Num Recovered</td> <td>" +
        country.recovered +
        "</td></tr>"

        );

        $("#selectbycountry > tbody").empty();
        country.province.map(function (name, province) {
            $("#selectbycountry > tbody").append(
                "<tr> <td>" +
                province[name].name +
                "</td>" +
                "<td>" +
                province[name].deaths +
                "</td> " +
                "<td>" +
                province[name].confirmed+
                "</td> " +
                "<td>" +
                province[name].recovered+"</td>" +
                "</tr>"
            );
        });
    };



    function plotMarkers(m)
    {
        initMap();
        markers = [];
        bounds = new google.maps.LatLngBounds();
        console.log(m);
        var position = new google.maps.LatLng(m.lat, m.lon);
        console.log(position);
        markers.push(
            new google.maps.Marker({
                position: position,
                map: map,
                animation: google.maps.Animation.DROP
            })
        );

        bounds.extend(position);
        map.fitBounds(bounds);
    }
    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: -34.397, lng: 150.644},
            zoom: 4
        });
    }

    function init (){
        initMap();
        api.getAllCases().then(function (data){
            _table(data);});

    }

    function getCasesByName(name) {
        api.getCasesByCountry(name).then(function (data){
            _tabled(data);
            plotMarkers(data)});
    }


    return {
        init:init,
        getCasesByName:getCasesByName

    };
}());