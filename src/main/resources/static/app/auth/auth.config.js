(function () {
  'use strict';

  angular
    .module('app.auth')
    .run(appRun);

  /* @ngInject */
  function appRun($rootScope, $http, authDefaults, authService) {
    authDefaults.authenticateUrl = 'http://localhost:8080/user';

    $rootScope.$on('login', () => {
      $http.defaults.headers.common.Authorization = authService.getAuth();
    });
  }
})();
