angular.module('app').controller('RotinaController', ['$scope', 'RotinaService', function ($scope, RotinaService) {

    $scope.listar = listar;
    $scope.procurar = procurar;
    $scope.selecionar = selecionar;
    $scope.criar = criar;
    $scope.excluir = excluir;

    $scope.total = 0;
    $scope.disponivel = 0;
    $scope.addTotal = addTotal;
    $scope.subTotal = subTotal;
    $scope.addDisponivel = addDisponivel;
    $scope.subDisponivel = subDisponivel;
 
    $scope.matches = [
        { 'nome': 'eu', 'foto': 'dois' },
        { 'nome': 'eudois', 'foto': 'fotodois' },
        { 'nome': 'eudois', 'foto': 'fotodois' },
        { 'nome': 'eudois', 'foto': 'fotodois' },
        { 'nome': 'eudois', 'foto': 'fotodois' }
    ];

    var destino = {
        'lat': '-29.794918',
        'lgn': '-51.146509'
    }

    console.log($scope.matches);

    function listar() {
        RotinaService
            .listar()
            .then(response => {
                console.log(response);
            })
    }

    function procurar() {
        RotinaService
            .procurar()
            .then(response => {
                console.log(response);
            });
    }

    function selecionar(rotina) {
        if (isUndefinedOrNull(rotina)) {
            console.log('undefined or null');
            return;
        }
        RotinaService
            .selecionar(rotina)
            .then(response => {
                console.log(response);
            });
    }

    function criar(rotina) {
        debugger;
        rotina.origem = origem;
        rotina.destino = destino;
        if (isUndefinedOrNull(rotina)) {
            console.log('undefined or null');
            return;
        } else if (rotina.motorista === true) {
            rotina.vagasTotais = $scope.total;
            rotina.vagasDisponiveis = $scope.disponivel;
        }

        var rotinaDiaSemana = [];
        for (var diaSemana in rotina.diaSemana) {
            rotinaDiaSemana.push(diaSemana);
        }

        console.log(RotinaDiaSemana);
        // console.log(rotina);
        // for (var diaSemana in rotina.diaSemana) {
        //     console.log(typeof (diaSemana) + ": " + diaSemana);
        // }
        RotinaService
            .criar(rotina)
            .then(response => {
                console.log(response);
            });
    }

    function excluir(rotina) {
        if (isUndefinedOrNull(rotina)) {
            console.log('undefined or null');
            return;
        }
        RotinaService
            .excluir(rotina)
            .then(response => {
                console.log(response);
            });
    }

    function addTotal() {
        $scope.total++;
    }

    function subTotal() {
        if ($scope.disponivel === $scope.total) {
            subDisponivel();
            setTimeout(subTotal, 100);
        } else if ($scope.total > 0) {
            $scope.total--;
            $scope.$digest();
        }
    }

    function addDisponivel() {
        if ($scope.disponivel < $scope.total) {
            $scope.disponivel++;
        }
    }

    function subDisponivel() {
        if ($scope.disponivel > 0) {
            $scope.disponivel--;
        }
    }

    function isUndefinedOrNull(object) {
        return (angular.isUndefined(object) || object === null);
    }


  autoComplete();

  var matrizPassageiro = [];
  var listaMotorista = [];
  var matrizMotoristas = [];
  var matrizPassageiro = [];
  var listaDistanciaRotina = [];

  function procurarMatchs(rotinaPassageiro){
    var respostaMetodoPrimeirasVerificacoes = verificarMatchHorarioEQuantidadeDeVagas(rotinaPassageiro);
    var respostaMatrix = matrix(rotinaPassageiro, respostaMetodoPrimeirasVerificacoes);
    var matchs = obterRotinasComMatchDistancia(rotinaPassageiro, respostaMatrix);
    return matchs;
  };

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

  function obterRotinasComMatchDistancia(rotinaPassageiro, listaDistanciaRotina) {
    rotinaService.getRotinasComMatchDistancia(rotinaPassageiro, listaDistanciaRotina).then(function (response) {
      listaDeMatchs = response.data;
    })
    return listaDeMatchs;
  };

  //API AUTOCOMPLETE
  function autoComplete() {
    $scope.autocomplete = new google.maps.places.Autocomplete(
      (document.getElementById('autocomplete'))
    );
    $scope.autocomplete.addListener('place_changed', pegarCoordenadas);
  }

  var origem;
  function pegarCoordenadas() {
    var place = $scope.autocomplete.getPlace();
    $scope.latitude = place.geometry.location.lat();
    $scope.longitude = place.geometry.location.lng();
    origem = {latitude:$scope.latitude, longitude:$scope.longitude};
  }


    function matrix(origemPassageiro, destinoPassageiro) {
    let origem = [origemPassageiro];
    let destino = [destinoPassageiro];

    new google.maps.DistanceMatrixService().getDistanceMatrix({
      origins: origem,
      destinations: destino,
      travelMode: 'DRIVING',
      unitSystem: google.maps.UnitSystem.METRIC,
      avoidHighways: false,
      avoidTolls: false
    }, function(response) {
      let matriz = response;
        var distanciaRetorno = matriz.rows[0].elements[0].distance.value;
      })
      return distanciaRetorno;
    }
}]);