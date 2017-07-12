angular.module('app').controller('RotinaBuscarController', ['$scope', 'RotinaService', 'SolicitacoesService', '$q',
  function ($scope, RotinaService, SolicitacoesService, $q) {

  listar();

  $scope.selecionar = selecionar;
  $scope.matches = [];
  $scope.procurarMatchs = procurarMatchs;
  $scope.enviarSolicitacao = enviarSolicitacao;

  function listar() {
    RotinaService.listar().then(response => {
      $scope.rotinas = response.data;
    })
  }

  function procurarMatchs(rotina) {
    RotinaService
    .getRotinasMatchHorarioEComVaga(rotina.idRotina)
    .then(response => {
      let listaDeRotinasMotorista = response.data;
      matrix(rotina, listaDeRotinasMotorista)
      .then(res => {
        let respostaMatrix = res;
        obterRotinasComMatchDistancia(rotina.idRotina, respostaMatrix);
        distancias = [];
      });
    })
  }

  function matrix(rotinaPassageiro, listaDeRotinasMotorista) {
    var matrixDeferred = $q.defer()
    var distancias = [];
    let matrizMotoristas = montarArraysMatriz(listaDeRotinasMotorista);
    let matrizPassageiro = [{ lat: rotinaPassageiro.idOrigem.latitude, lng: rotinaPassageiro.idOrigem.longitude }];

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
        distancias.push({ rotina: listaDeRotinasMotorista[i], distancia: distanciaRetorno });
        i++;
      }
      i = 0;
      matrixDeferred.resolve(distancias);
    })
    return matrixDeferred.promise;
  }

  function obterRotinasComMatchDistancia(idRotina, listaDistanciaRotina) {
    RotinaService.getRotinasComMatchDistancia(idRotina, listaDistanciaRotina).then(function (response) {
      $scope.matches = response.data;
    })
  };

  function montarArraysMatriz (lista) {
    let auxiliar = [];
    lista.forEach(function (elemento) {
      let objetoMatrix = { lat: elemento.idOrigem.latitude, lng: elemento.idOrigem.longitude };
      auxiliar.push(objetoMatrix);
    });
    return auxiliar;
  }

  function enviarSolicitacao(rotina) {
    let solicitacao = {usuarioAlvo: rotina.usuario};
    console.log(rotina);
    console.log(solicitacao);
    SolicitacoesService.enviar(solicitacao).then(res => console.log(res));
  }

  // TODO: Implementar utilização de Selecionar ou remover método
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

}]);
