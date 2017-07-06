angular.module('app').controller('LoginController', function ($scope) {

  $scope.login = login;
  $scope.logout = logout;
  importGPlusScript();

  function onLoad() {
    gapi.load('auth2', function() {
      auth2 = gapi.auth2.init({
      });
    });
  }

  function login (googleUser) {
      // var profile = auth2.currentUser.get().getBasicProfile();
      var profile = googleUser.getBasicProfile();
      console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
      console.log('Name: ' + profile.getName());
      console.log('Image URL: ' + profile.getImageUrl());
      console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
  }

  function logout () {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      alert('Logout realizado');
    })
  };

  function importGPlusScript() {
    var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
    po.src = 'https://apis.google.com/js/client:plusone.js';
    po.onload = function() {
      onLoad();
    }
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(po, s);
  };

  function onSignIn(googleUser) {
    console.log(googleUser);
    var profile = googleUser.getBasicProfile();
    console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
  }

   window.login = login;

});
