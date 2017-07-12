angular.module('app').factory('UsuarioService', ['$http', function($http) {    
    let porta = 9090;
    let url = `http://localhost:${porta}/api/usuarios`;

    function criar(usuario) {
        return $http({
            url: `${url}`,
            method: 'POST',
            data: usuario
        });
    }

    return ({
        criar: criar
    });
}]);
