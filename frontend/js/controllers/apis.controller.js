modulo.controller('apisController', ['$scope', function ($scope) {


//API matrix
$scope.matrizMotoristas = [];
$scope.matrizPassageiro = [];
$scope.distancias = [];
  //alterar para passar duas listas de parametro
$scope.matrix = function() {
  //pok
  $scope.listaMotorista = [{idOrigem:{latitude:-30.0624354, longitude:-51.1749197}, idUsuario:5},
                        {idOrigem:{latitude:-30.0153303, longitude:-51.1130727}, idUsuario:4},
                        {idOrigem:{latitude:-23.5505199, longitude:-46.6333094}, idUsuario:6}]
  console.log($scope.listaMotorista);
  $scope.matrizMotoristas = [];
  $scope.matrizPassageiro = [{lat:-29.7949175,lng:-51.1465092}];
  $scope.listaMotorista.forEach(function(motorista){
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
    console.log(response);
    var i = 0;
    for (var linha in $scope.matriz.rows) {
      var distanciaRetorno = $scope.matriz.rows[i].elements[0].distance.value;
      console.log(distanciaRetorno);
      $scope.distancias.push({motorista:$scope.listaMotorista[0], distancia:distanciaRetorno});
      console.log($scope.distancias);
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
