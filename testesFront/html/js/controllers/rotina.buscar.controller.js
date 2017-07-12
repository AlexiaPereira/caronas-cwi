angular.module('app').controller('RotinaBuscarController', ['$scope', 'RotinaService', '$q', function ($scope, RotinaService, $q) {

  // listar();
  // console.log($scope.rotinas);
  listar();

  $scope.procurar = procurar;
  $scope.selecionar = selecionar;

  $scope.distancia = 0;

  $scope.clique = clique;
  function clique(rotina) {
    console.log(rotina);
    procurarMatchs(rotina);
  }

  // var destino = {
  //   lat: -29.794918,
  //   lng: -51.146509
  // }


  function listar() {
    RotinaService
    .listar()
    .then(response => {
      $scope.rotinas = response.data;
    })
  }

  function procurar() {
    RotinaService
    .procurar()
    .then(response => {
      console.log(response);
    });
  }

  function selecionar(idRotina) {
    if (isUndefinedOrNull(idRotina)) {
      console.log('undefined or null');
      return;
    }
    RotinaService
    .selecionar(idRotina)
    .then(response => {
      console.log(response);
    });
  }

  function isUndefinedOrNull(object) {
    return (angular.isUndefined(object) || object === null);
  }


  var matrizPassageiro = [];
  var listaMotorista = [];
  var matrizMotoristas = [];
  var matrizPassageiro = [];
  var listaDistanciaRotina = [];

  $scope.matches = [];
  $scope.procurarMatchs = procurarMatchs;
  function procurarMatchs(rotina) {
    function async() {
      return $q(function(resolve, reject) {
        setTimeout(function() {
          verificarMatchHorarioEQuantidadeDeVagas(rotina.idRotina);
        }, 1000);
      });
    }
    matchMatrix(rotina);
    // verificarMatchHorarioEQuantidadeDeVagas(rotina.idRotina);
    // .then
  }

  function matchMatrix(rotina) {
    // verificarMatchHorarioEQuantidadeDeVagas(rotina.idRotina);
    RotinaService
    .getRotinasMatchHorarioEComVaga(rotina.idRotina)
    .then(response => {
      var listaDeRotinasMotorista = response.data;
      console.log('lista de rotinas motorista');
      console.log(listaDeRotinasMotorista);
      console.log('----------------------------');
      matrix(rotina, listaDeRotinasMotorista)
      .then(res => {
        var respostaMatrix = res;
        console.log('resultado matrix');
        console.log(respostaMatrix);
        console.log('----------------------------');
        obterRotinasComMatchDistancia(rotina.idRotina, respostaMatrix);
        // return matchs;
      });
    })
  }
  // };

  // function verificarMatchHorarioEQuantidadeDeVagas(idRotina) {
  //   RotinaService
  //   .getRotinasMatchHorarioEComVaga(idRotina)
  //   .then(response => {
  //     var listaDeRotinasMotorista = response.data;
  //     console.log(listaDeRotinasMotorista);
  //   })
  // };

  function matrix(rotinaPassageiro, listaDeRotinasMotorista) {
    var matrixDeferred = $q.defer()
    var distancias = [];
    listaDeRotinasMotorista.forEach(function (motorista) {
      let objetoMotorista = { lat: motorista.idOrigem.latitude, lng: motorista.idOrigem.longitude };
      matrizMotoristas.push(objetoMotorista);
    });
    let objetoPassageiro = { lat: rotinaPassageiro.idOrigem.latitude, lng: rotinaPassageiro.idOrigem.longitude };
    matrizPassageiro.push(objetoPassageiro);

    new google.maps.DistanceMatrixService().getDistanceMatrix({
      origins: matrizMotoristas,
      destinations: matrizPassageiro,
      travelMode: 'DRIVING',
      unitSystem: google.maps.UnitSystem.METRIC,
      avoidHighways: false,
      avoidTolls: false
    }, function (response) {
      let matriz = response;
      let i = 0;
      for (var linha in matriz.rows) {
        var distanciaRetorno = matriz.rows[i].elements[0].distance.value;
        distancias.push({ motorista: listaDeRotinasMotorista[i], distancia: distanciaRetorno });
        i++;
      }
      matrixDeferred.resolve(distancias);
    })
    return matrixDeferred.promise;
  }

  function obterRotinasComMatchDistancia(idRotina, listaDistanciaRotina) {
    RotinaService.getRotinasComMatchDistancia(idRotina, listaDistanciaRotina).then(function (response) {
      $scope.matches = response.data;
    })
  };

}]);
