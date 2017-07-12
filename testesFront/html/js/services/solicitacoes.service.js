angular.module('app').factory('SolicitacoesService', ['$http', function ($http) {

    let porta = 9090;
    let url = `http://localhost:${porta}/api/solicitacoes`;

    function enviar(solicitacao) {
        return $http({
            url: `${url}`,
            method: 'POST',
            data: solicitacao
        });
    }

    function aceitar(solicitacaoDTO) {
        return $http({
            url: `${url}/aceitar`,
            method: 'POST',
            data: solicitacaoDTO
        });
    }

    function recusar(idSolicitacao) {
        return $http.delete(`${url}/${idSolicitacao}`);
    }

    function buscarPendentes(idUsuario) {
        $http.get(`${url}/pendentes/${idUsuario}`);
    }

    return ({
        enviar: enviar,
        aceitar: aceitar,
        recusar: recusar,
        buscarPendentes: buscarPendentes
    });
}]);
