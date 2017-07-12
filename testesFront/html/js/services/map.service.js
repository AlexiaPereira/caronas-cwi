angular.module('app').factory('MapService', ['$http', function ($http) {

  function iniciarMapa(local) {
    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 12,
      center: local
    });
    var marker = new google.maps.Marker({
      position: local,
      map: map
    });
  }


    return ({
        iniciarMapa:iniciarMapa
    });
}]);
