(function () {
  'use strict';

  angular
    .module('app.repos')
    .factory('Repo', Repo);

  /* @ngInject */
  function Repo(DS) {
    return DS.defineResource({
      name: 'repo',
      endpoint: '/repos'
    });
  }
})();
