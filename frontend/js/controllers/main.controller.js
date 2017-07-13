angular.module('app').controller('MainController', ['$scope', 'authService', 'MapService', function ($scope, authService, MapService) {

    $scope.logout = logout;

    function logout() {
        function FacebookLogout() {
            FB.logout(response => {
                console.log(response);
            });
        }
        function GoogleLogout() {
            let auth2 = gapi.auth2.getAuthInstance();
            auth2.signOut().then(function () {
                console.log('User signed out.');
            });
        }
    }

    var cwi = { lat: -29.7646612, lng: -51.1435347 };
    mapaCWI(cwi);

    function mapaCWI(cwi) {
        MapService.iniciarMapa(cwi);
    }

}]);
