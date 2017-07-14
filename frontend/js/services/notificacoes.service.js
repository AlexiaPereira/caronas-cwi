angular.module('app').factory('NotificacoesService', ['$http', function ($http) {

    let porta = 9090;
    let url = `http://localhost:${porta}/api/notificacoes`;


    function deletarNotificacoes() {
        return $http.delete(`${url}`);
    }

    function getNotificacoes() {
        return $http.get(`${url}`);
    }

    return ({
        deletarNotificacoes: deletarNotificacoes,
        getNotificacoes: getNotificacoes
    });
}]);
