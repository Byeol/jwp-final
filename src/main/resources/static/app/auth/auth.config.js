(function () {
  'use strict';

  angular
    .module('app.auth')
    .run(appRun);

  /* @ngInject */
  function appRun($rootScope, $http, authDefaults, authService, Layout, Constant) {
    authDefaults.authenticateUrl = `${Constant.baseUrl}/user`;

    if (authService.getAuth()) {
      login();
    }

    $rootScope.$on('login', login);
    $rootScope.$on('logout', logout);

    function login() {
      $http.defaults.headers.common.Authorization = authService.getAuth();
      Layout.status.login = true;
      Layout.status.username = authService.username();
    }

    function logout() {
      delete $http.defaults.headers.common.Authorization;
      Layout.status.login = false;
      Layout.status.username = null;
    };
  }
})();
