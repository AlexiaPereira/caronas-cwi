// FACEBOOK
window.fbAsyncInit = function () {
    FB.init({
        appId: '333401583755791',
        autoLogAppEvents: true,
        xfbml: true,
        version: 'v2.9'
    });
    FB.AppEvents.logPageView();
};

(function (d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) { return; }
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/pt_BR/sdk.js#xfbml=1&version=v2.9";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// GOOGLE
GoogleInit();
function GoogleInit() {
    let auth2;
    gapi.load('auth2', function () {
        auth2 = gapi.auth2.init({
            client_id: '717564533652-l8vgbradmcpthio4f7t18gd4290nqql8.apps.googleusercontent.com',
            fetch_basic_profile: true,
            scope: 'profile'
        })
    })
    return auth2;
}
