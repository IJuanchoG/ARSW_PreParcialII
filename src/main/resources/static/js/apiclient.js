apiclient = (function() {

    return {
        getCasesByCountry: function(name) {
            var getPromise=$.ajax({
                dataType: "json",
                url: "/covid19/country/"+name,
            });
            return getPromise;
        },getAllCases: function() {
            var getPromise=$.ajax({
                dataType: "json",
                url: "/covid19/AllCountries",
            });
            return getPromise;
        }
    };
})();