angular.module('app').controller('MainController', ['$scope', 'authService', function ($scope, authService) {

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
}]);
