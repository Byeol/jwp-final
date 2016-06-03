(function () {
  'use strict';

  angular
    .module('app.issues')
    .factory('Collaborator', Collaborator);

  /* @ngInject */
  function Collaborator(DS, Repo) {
    return DS.defineResource({
      name: 'collaborator',
      endpoint: '/collaborators',
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
