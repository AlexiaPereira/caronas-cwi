modulo.controller('apisController', ['$scope', function ($scope) {


//API matrix
$scope.matrizMotoristas = [];
$scope.matrizPassageiro = [];
$scope.distancias = [];

//os objetos do arrays tem que vir no formato {lat: valor, lng: valor}, podemos ajustar no front, se necessário
$scope.matrix = function(listaMotoristas, passageiro) {
  $scope.matrizMotoristas = listaMotoristas;
  $scope.matrizPassageiro = passageiro;

  new google.maps.DistanceMatrixService().getDistanceMatrix({
    origins: $scope.matrizMotoristas,
    destinations: $scope.matrizPassageiro,
    travelMode: 'DRIVING',
    unitSystem: google.maps.UnitSystem.METRIC,
    avoidHighways: false,
    avoidTolls: false
  }, function(response) {
    $scope.matriz = response;

    var i = 0;
    for (var linha in $scope.matriz.rows) {
      $scope.distancia = $scope.matriz.rows[i].elements[0].distance.value;
      $scope.distancias.push($scope.distancia);
      i++;
    }
  })
}



//API AUTOCOMPLETE
$scope.click = function() {
   $scope.autocomplete = new google.maps.places.Autocomplete(
      (document.getElementById('autocomplete'))
    );
  $scope.autocomplete.addListener('place_changed', pegarCoordenadas);
}

function pegarCoordenadas() {
  var place = $scope.autocomplete.getPlace();
  $scope.latitude = place.geometry.location.lat();
  $scope.longitude = place.geometry.location.lng();
  $scope.origem = {latitude:$scope.latitude, longitude:$scope.longitude};
}






}]);
