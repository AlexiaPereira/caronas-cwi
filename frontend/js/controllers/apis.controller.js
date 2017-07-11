angular.module('app').controller('apisController', ['$scope', function ($scope) {

  autoComplete();

  var matrizPassageiro = [];
  var listaMotorista = [];
  var matrizMotoristas = [];
  var matrizPassageiro = [];
  var listaDistanciaRotina = [];


  function verificarMatchHorarioEQuantidadeDeVagas(rotinaPassageiro){
    rotinaService.getRotinasMatchHorarioEComVaga(rotinaPassageiro).then(function (response) {
      listaDeRotinasMotorista = response.data;
    })
    return listaDeRotinasMotorista;
  };

  function matrix(rotinaPassageiro, listaDeRotinasMotorista) {
    /*listaMotorista = [{idOrigem:{latitude:-30.0624354, longitude:-51.1749197}, idUsuario:5},
    {idOrigem:{latitude:-30.0153303, longitude:-51.1130727}, idUsuario:4},
    {idOrigem:{latitude:-23.5505199, longitude:-46.6333094}, idUsuario:6}]
    matrizPassageiro = [{lat:-29.7949175,lng:-51.1465092}];*/
    listaDeRotinasMotorista.forEach(function(motorista){
      let objetoMotorista = {lat:motorista.idOrigem.latitude, lng:motorista.idOrigem.longitude};
      matrizMotoristas.push(objetoMotorista);
    });
    let objetoPassageiro = {lat:rotinaPassageiro.idOrigem.latitude, lng:rotinaPassageiro.idOrigem.longitude};
    matrizPassageiro.push(objetoPassageiro);

    new google.maps.DistanceMatrixService().getDistanceMatrix({
      origins: matrizMotoristas,
      destinations: matrizPassageiro,
      travelMode: 'DRIVING',
      unitSystem: google.maps.UnitSystem.METRIC,
      avoidHighways: false,
      avoidTolls: false
    }, function(response) {
      let matriz = response;
      let i = 0;
      for (var linha in matriz.rows) {
        var distanciaRetorno = matriz.rows[i].elements[0].distance.value;
        distancias.push({motorista:listaMotorista[0], distancia:distanciaRetorno});
        i++;
      }
    })
    return listaDistanciaRotina;
  }

  //API AUTOCOMPLETE
  function autoComplete() {
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
