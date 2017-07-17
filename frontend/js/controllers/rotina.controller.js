angular.module('app').controller('RotinaController', ['$scope', 'NotificacoesService', 'RotinaService', 'MapService', '$q', '$location', function ($scope, NotificacoesService, RotinaService, MapService, $q, $location) {

    $scope.listar = listar;
    $scope.criar = criar;
    $scope.excluir = excluir;
    $scope.excluirNotificacoes = excluirNotificacoes;
    $scope.disponivel = 0;
    $scope.addDisponivel = addDisponivel;
    $scope.subDisponivel = subDisponivel;
    $scope.rotinasPassageiro = [];
    $scope.rotinasMotorista = [];
    $scope.distancia = 0;

    listar();
    autoComplete();
    getNotificacoes();

    //variáveis auxiliares
    var origem;
    var destino = {lat: -29.794918, lng: -51.146509}

    function listar() {
        RotinaService
            .listar()
            .then(response => {
                let rotinas = response.data;
                if (rotinas.length === 0) {
                    $location.path('/rotina-cadastrar');
                }
                rotinas.forEach(function (rotina) {
                    if (!rotina.passageiro) {
                        $scope.rotinasMotorista.push(rotina);
                    }
                    else {
                        $scope.rotinasPassageiro.push(rotina);
                    }
                });
            });
    }

    function criar(rotina) {
        if (isUndefinedOrNull(rotina)) {
            return;
        }
        rotina.origem = origem;
        rotina.destino = { endereco: 'CWI SOFTWARE', latitude: destino.lat, longitude: destino.lng };
        rotina.vagasDisponiveis = $scope.disponivel || 0;
        rotina.duracao = 0;
        distanciaRotina({ lat: $scope.latitude, lng: $scope.longitude }, destino).then(res => {
            rotina.distancia = res;

            var aux = [];
            for (var diaSemana in rotina.rotinaDiaSemanaList) {
                aux.push({
                        'diaSemana':
                        { 'nome': diaSemana },
                        'vagasDisponiveis': rotina.vagasDisponiveis,
                    });
            }
            rotina.rotinaDiaSemanaList = aux;
            RotinaService
                .criar(rotina)
                .then(response => {
                    rotina.rotinaDiaSemanaList = null;
                    if (rotina.passageiro) {
                        $location.path('/rotina-visualizar');
                    } else {
                        $location.path('/meus-grupos');
                    }
                }, res => {
                    rotina.rotinaDiaSemanaList = null;
                });
        });
        rotina.origem = '';
    }

    function excluir(idRotina) {
        if (isUndefinedOrNull(idRotina)) {
            console.log('undefined or null');
            return;
        }
        RotinaService
            .excluir(idRotina)
            .then(response => {
                console.log(response);
            });
    }

    function addDisponivel() {
        $scope.disponivel++;
    }

    function subDisponivel() {
        if ($scope.disponivel > 0) {
            $scope.disponivel--;
        }
    }

    function isUndefinedOrNull(object) {
        return (angular.isUndefined(object) || object === null);
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
        origem = { endereco: place.formatted_address, latitude: $scope.latitude, longitude: $scope.longitude };
        var origemMap = {lat: $scope.latitude, lng: $scope.longitude};
        rota (origemMap);
    }

    function rota (local) {
      MapService.rota(local);
    }

    function distanciaRotina(origemPassageiro, destinoPassageiro) {
        let origem = [origemPassageiro];
        let destino = [destinoPassageiro];
        var deferred = $q.defer();
        new google.maps.DistanceMatrixService().getDistanceMatrix({
            origins: origem,
            destinations: destino,
            travelMode: 'DRIVING',
            unitSystem: google.maps.UnitSystem.METRIC,
            avoidHighways: false,
            avoidTolls: false
        }, function (response) {
            var matriz = response;
            var distanciaRetorno = matriz.rows[0].elements[0].distance.value;
            deferred.resolve(distanciaRetorno);
        })
        return deferred.promise;
    }

    function getNotificacoes() {
      NotificacoesService.getNotificacoes().then(res => {
        $scope.notificacoes = res.data
        getNotificacoes();
      });
    }

    function excluirNotificacoes() {
      NotificacoesService.deletarNotificacoes();
    }

}])
