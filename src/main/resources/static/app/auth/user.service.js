(function () {
  'use strict';

  angular
    .module('app.auth')
    .factory('User', User);

  /* @ngInject */
  function User(DS, Repo) {
    return DS.defineResource({
      name: 'user',
      endpoint: '/users'
    });
  }
})();
