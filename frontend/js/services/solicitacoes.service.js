angular.module('app').factory('SolicitacoesService', ['$http', function ($http) {

    let porta = 9090;
    let url = `http://localhost:${porta}/api/solicitacoes`;

    function enviar(solicitacaoDTO) {
        return $http({
            url: `${url}`,
            method: 'POST',
            data: solicitacaoDTO
        });
    }

    function aceitar(solicitacao) {
        return $http({
            url: `${url}/aceitar`,
            method: 'POST',
            data: solicitacao
        });
    }

    function recusar(idSolicitacao) {
        return $http.delete(`${url}/${idSolicitacao}`);
    }

    function buscarPendentes() {
        return $http.get(`${url}/pendentes`);
    }

    return ({
        enviar: enviar,
        aceitar: aceitar,
        recusar: recusar,
        buscarPendentes: buscarPendentes
    });
}]);
