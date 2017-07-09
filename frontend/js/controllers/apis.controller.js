modulo.controller('apisController', ['$scope', function ($scope) {


//API matrix
$scope.matrizMotoristas = [];
$scope.matrizPassageiro = [];
$scope.distancias = [];
  //alterar para passar duas listas de parametro
$scope.matrix = function() {
  //pok
  var listaMotorista = [{idOrigem:{latitude:30, longitude:40}},
                        {idOrigem:{latitude:29.99, longitude:40}},
                        {idOrigem:{latitude:29.9999999, longitude:40}}]
  console.log(listaMotorista);
  $scope.matrizMotoristas = [];
  $scope.matrizPassageiro = {lat:30.000,lng:40};
  listaMotorista.forEach(function(motorista){
    var objeto = {lat:motorista.idOrigem.latitude, lng:motorista.idOrigem.longitude};
    $scope.matrizMotoristas.push(objeto);
    console.log($scope.matrizMotoristas);
  });

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
