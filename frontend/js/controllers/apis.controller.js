modulo.controller('apisController', ['$scope', function ($scope) {


  //API matrix
  $scope.matrizMotoristas = [];
  $scope.matrizPassageiro = [];
  $scope.distancias = [];
  //alterar para passar duas listas de parametro
  $scope.matrix = function() {
    //pok
    $scope.listaArrayMotorista = [[{idOrigem:{latitude:-29.7949175, longitude:-51.1465092}, idUsuario:1},
      {idOrigem:{latitude:-29.7949175, longitude:-51.1130727}, idUsuario:2},
      {idOrigem:{latitude:-23.5505199, longitude:-46.6333094}, idUsuario:3}]]
        $scope.matrizPassageiro = [{lat:-29.7949175,lng:-51.1465092}];
        var j = 0;
        $scope.listaArrayMotorista.forEach(function(listaMotorista){
          debugger
          listaMotorista.forEach(function(motorista){
            console.log(motorista);
            var objeto = {lat:motorista.idOrigem.latitude, lng:motorista.idOrigem.longitude};
            //console.log($scope.matrizMotoristas);
            $scope.matrizMotoristas.push(objeto);
            //console.log($scope.matrizMotoristas);
          })
          //console.log($scope.matrizMotoristas);
          //console.log($scope.matrizPassageiro);
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
              var distanciaRetorno = $scope.matriz.rows[i].elements[0].distance.value;
              $scope.distancias.push({listaMotorista:$scope.listaArrayMotorista[j], distancia:distanciaRetorno});
              console.log("cai");
              console.log($scope.distancias);
              i++;
            }
            console.log($scope.distancias);
            $scope.arrayDeArraysMotoristas.push($scope.distancias);
            console.log($scope.arrayDeArraysMotoristas);
            $scope.distancias = [];
          })
          j++;
        });
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
