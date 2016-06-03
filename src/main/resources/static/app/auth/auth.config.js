(function () {
  'use strict';

  angular
    .module('app.auth')
    .run(appRun);

  /* @ngInject */
  function appRun($rootScope, $http, authDefaults, authService) {
    authDefaults.authenticateUrl = '/user';

    $rootScope.$on('login', () => {
      $http.defaults.headers.common.Authorization = authService.getAuth();
    });
  }
})();
