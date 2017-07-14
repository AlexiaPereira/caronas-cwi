angular.module('app').controller('MainController', ['$scope', 'authService', 'MapService', function ($scope, authService, MapService) {


    $scope.usuario = localStorage.getItem('nome');
    $scope.foto = localStorage.getItem('foto');
    
    $scope.logout = logout;

    function logout() {
        window.FB.logout(response => {
            console.log(response);
        });

        let auth2 = window.gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
            console.log('User signed out.');
        });

        localStorage.clear();
    }

    var cwi = {lat: -29.7954709, lng: -51.1584814};
    mapaCWI(cwi);

    function mapaCWI(cwi) {
        MapService.iniciarMapa(cwi);
    }

}]);