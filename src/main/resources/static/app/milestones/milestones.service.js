(function () {
  'use strict';

  angular
    .module('app.milestones')
    .factory('Milestone', Milestone);

  /* @ngInject */
  function Milestone(DS, Repo) {
    return DS.defineResource({
      name: 'milestone',
      endpoint: '/milestones',
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
