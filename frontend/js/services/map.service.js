angular.module('app').factory('MapService', ['$http', function ($http) {

  function iniciarMapa(local) {
    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 11,
      center: local
    });
    var marker = new google.maps.Marker({
      position: local,
      map: map
    });
  }

  function rota(local) {
    var directionsDisplay = new google.maps.DirectionsRenderer;
    var directionsService = new google.maps.DirectionsService;
    var map = new google.maps.Map(document.getElementById('legal'), {
      zoom: 16,
      center: {lat: -29.7874, lng:  -51.1412}
    });
    directionsDisplay.setMap(map);
    calculateAndDisplayRoute(directionsService, directionsDisplay, local);
  }

  function calculateAndDisplayRoute(directionsService, directionsDisplay, local) {
    directionsService.route({
      origin: {lat: -29.7954709, lng: -51.1584814},
      destination: local,
      travelMode: google.maps.TravelMode.DRIVING
    }, function(response, status) {
      if (status == 'OK') {
        directionsDisplay.setDirections(response);
      }
    });
  }

  function mapaMatch(arrayDeLocais) {

    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 8,
      center: {lat: -29.7646612, lng:  -51.1435347}
    });

    // Create an array of alphabetical characters used to label the markers.
    var labels = '123456789';

    // Add some markers to the map.
    // Note: The code uses the JavaScript Array.prototype.map() method to
    // create an array of markers based on a given "locations" array.
    // The map() method here has nothing to do with the Google Maps API.
    var markers = arrayDeLocais.map(function(location, i) {
      return new google.maps.Marker({
        position: location,
        label: labels[i % labels.length]
      });
    });
    var markerCluster = new MarkerClusterer(map, markers,
      {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
    }



    return ({
      iniciarMapa: iniciarMapa,
      mapaMatch: mapaMatch,
      rota: rota
    });
  }]);
