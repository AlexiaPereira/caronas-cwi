angular.module('app').factory('RotinaService', ['$http', function($http) {

    let porta = 123;
    let url = `http://localhost:${porta}/api/rotinas`;

    function listar() {
        return $http.get(`${url}/usuario`);
    }

    function procurar() {
        return $http.get(`${url}`);
    }

    function getRotinasMatchHorarioEComVaga(rotinaPassageiro) {
        return $http.get(`${url}/match`);
    }

    function getRotinasComMatchDistancia(rotinaPassageiro, listaDistanciaRotina) {
        return $http.get(`${url}/match-distancia`);
    }

    function selecionar(rotina) {
        return $http({
            url: `${url}/selecionar`,
            method: 'POST',
            data: rotina
        });
    }

    function criar(rotina) {
        return $http({
            url: `${url}/criar`,
            method: 'POST',
            data: rotina
        });
    }

    function excluir(rotina) {
        return $http({
            url: `${url}/excluir`,
            method: 'POST',
            data: rotina
        });
    }

    return ({
        listar: listar,
        procurar: procurar,
        selecionar: selecionar,
        criar: criar,
        excluir: excluir,
        getRotinasMatchHorarioEComVaga: getRotinasMatchHorarioEComVaga,
        getRotinasComMatchDistancia: getRotinasComMatchDistancia
    });
}]);
