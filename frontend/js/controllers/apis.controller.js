modulo.controller('apisController', ['$scope', function ($scope) {

$scope.matriz_lado_A = [];
$scope.matriz_lado_B = [];

$scope.click = function getMatrix() {
  var origin1 = {lat: -30.0627024, lng: -51.1613968};
  var origin2 = 'Greenwich, England';
  var origin3 = {lat: -30.0627024, lng: -51.1613968};
  $scope.matriz_lado_A.push(origin1);
  $scope.matriz_lado_A.push(origin2);
  $scope.matriz_lado_A.push(origin1);
  $scope.matriz_lado_A.push(origin2);
  var valor1 = {lat: -28.0627024, lng: -51.1613968};
  var valor2 = {lat: 50.087, lng: 14.421};
  var valor3 = {lat: -30.0627024, lng: -51.1613968};
  $scope.matriz_lado_B.push(valor1);
  $scope.matriz_lado_B.push(valor2);
  $scope.matriz_lado_B.push(valor3);

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
  })
}
}]);
