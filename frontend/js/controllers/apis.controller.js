modulo.controller('apisController', ['$scope', function ($scope) {

$scope.matrizMotoristas = [];
$scope.matrizPassageiro = [];
$scope.distancias = [];

//os objetos do arrays tem que vir no formato {lat: valor, lng: valor}, podemos ajustar no front, se necessário
$scope.clicka = function(listaMotoristas, passageiro) {
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


var placeSearch, autocomplete;
var componentForm = {
  street_number: 'short_name',
  route: 'long_name',
  locality: 'long_name',
  administrative_area_level_1: 'short_name',
  country: 'long_name',
  postal_code: 'short_name'
};


$scope.click = function() {
  debugger
   $scope.autocomplete = new google.maps.places.Autocomplete(
      (document.getElementById('autocomplete')),
      {types: ['geocode']});
  $scope.autocomplete.addListener('place_changed', fillInAddress);
}

function fillInAddress() {
  var place = $scope.autocomplete.getPlace();
  var latitude = place.geometry.location.lat();
  var longitude = place.geometry.location.lng();
  console.log(latitude, longitude);

  for (var component in componentForm) {
    document.getElementById(component).value = '';
    document.getElementById(component).disabled = false;
  }
  for (var i = 0; i < place.address_components.length; i++) {
    var addressType = place.address_components[i].types[0];
    if (componentForm[addressType]) {
      var val = place.address_components[i][componentForm[addressType]];
      document.getElementById(addressType).value = val;
    }
  }
}






}]);
