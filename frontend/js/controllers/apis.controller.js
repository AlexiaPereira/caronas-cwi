modulo.controller('apisController', ['$scope', function ($scope) {

$scope.click = function getMatrix() {
  var origin1 = {lat: -30.0627024, lng: -51.1613968};
  var origin2 = 'Greenwich, England';
  var origin3 = {lat: -30.0627024, lng: -51.1613968};
  var destinationA = 'Stockholm, Sweden';
  var destinationB = {lat: 50.087, lng: 14.421};

  new google.maps.DistanceMatrixService().getDistanceMatrix({
    origins: [origin1, origin2, origin3],
    destinations: [destinationA, destinationB],
    travelMode: 'DRIVING',
    unitSystem: google.maps.UnitSystem.METRIC,
    avoidHighways: false,
    avoidTolls: false
  }, function(response, status) {
    console.log(response);
  })
}
}]);
