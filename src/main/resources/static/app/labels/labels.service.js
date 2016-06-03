(function () {
  'use strict';

  angular
    .module('app.labels')
    .factory('Label', Label);

  /* @ngInject */
  function Label(DS, Repo) {
    return DS.defineResource({
      name: 'label',
      endpoint: '/labels',
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
