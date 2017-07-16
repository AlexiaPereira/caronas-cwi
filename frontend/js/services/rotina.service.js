angular.module('app').factory('RotinaService', ['$http', function ($http) {

    let porta = 9090;
    let url = `http://localhost:${porta}/api/rotinas`;

    function listar() {
        return $http.get(`${url}/usuario`);
    }

    function getRotinasMatchHorarioEComVaga(idRotina) {
        return $http.get(`${url}/match/horario/${idRotina}`);
    }

    function getRotinasComMatchDistancia(idRotina, listaDistanciaRotina) {
        return $http.post(`${url}/match/distancia/${idRotina}`, listaDistanciaRotina);
    }

    function listarPorPassageiro (passageiro) {
      return $http.get(`${url}/usuario/passageiro/${passageiro}`);
    }

    function criar(rotina) {
        return $http({
            url: `${url}`,
            method: 'POST',
            data: rotina
        });
    }

    function excluir(idRotina) {
        return $http.delete(`${url}/${idRotina}`);
    }

    return ({
        listar: listar,
        criar: criar,
        excluir: excluir,
        getRotinasMatchHorarioEComVaga: getRotinasMatchHorarioEComVaga,
        getRotinasComMatchDistancia: getRotinasComMatchDistancia,
        listarPorPassageiro: listarPorPassageiro
    });
}]);
