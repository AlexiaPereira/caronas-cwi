angular.module('app').controller('MainController', ['$scope', 'authService', 'MapService', '$window', function ($scope, authService, MapService, $window) {

  google.maps.event.addDomListener(window, 'load',  mapFixed);

  $scope.usuario = localStorage.getItem('nome');
  $scope.foto = localStorage.getItem('foto');
  $scope.logout = logout;
  var cwi = {lat: -29.7954709, lng: -51.1584814};

  mapaCWI(cwi);

  function mapFixed() {
    var divMap = document.getElementById('map');
    divMap.style.position = 'fixed';
  }

  function logout() {
    function FabebookLogout() {
      FB.logout(response => {
        console.log(response);
      });
    }

    function GoogleLogout() {
      let auth2 = window.gapi.auth2.getAuthInstance();
      auth2.signOut().then(function () {
        console.log('User signed out.');
      });
    }

    localStorage.clear();
    sessionStorage.clear();
    $window.location.reload();
  }

  function mapaCWI(cwi) {
    MapService.iniciarMapa(cwi);
  }

}]);
