(function () {
  'use strict';

  angular
    .module('app.issues')
    .factory('Issue', Issue);

  /* @ngInject */
  function Issue(DS, Repo) {
    return DS.defineResource({
      name: 'issue',
      endpoint: '/issues',
      relations: {
        belongsTo: {
          repo: {
            parent: true,
            localKey: 'repoId',
            localField: 'repo'
          }
        }
      }
    });
  }
})();
