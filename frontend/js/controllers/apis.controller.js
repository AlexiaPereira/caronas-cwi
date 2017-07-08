modulo.controller('apisController', ['$scope', function ($scope) {

$scope.matriz_lado_A = [];
$scope.matriz_lado_B = [];

$scope.click = function getMatrix() {
  var origin1 = {lat: -30.0627024, lng: -51.1613968};
  var origin2 = {lat: -25.0627024, lng: -51.1613968};
  $scope.matriz_lado_A.push(origin1);
  $scope.matriz_lado_A.push(origin2);
  var valor1 = {lat: -28.0627024, lng: -53.1613968};
  $scope.matriz_lado_B.push(valor1);

  new google.maps.DistanceMatrixService().getDistanceMatrix({
    origins: $scope.matriz_lado_A,
    destinations: $scope.matriz_lado_B,
    travelMode: 'DRIVING',
    unitSystem: google.maps.UnitSystem.METRIC,
    avoidHighways: false,
    avoidTolls: false
  }, function(response) {
    $scope.matriz = response;
    console.log($scope.matriz);
    $scope.distancia = $scope.matriz.rows[0].elements[0].distance.value;
    console.log($scope.distancia);

    //pegar distancia de cada linha
    var i = 0;
    for (var linha in $scope.matriz.rows) {
      $scope.distancia = $scope.matriz.rows[i].elements[0].distance.value;
      console.log($scope.distancia);
      i++;
    }
    /*for(i = 0; i < $scope.matriz.rows.length; i++) {
      console.log("here");
      $scope.distancia = $scope.matriz.rows[i].elements[i].distance.value;
      console.log($scope.distancia);
    }*/
  })
}







}]);
