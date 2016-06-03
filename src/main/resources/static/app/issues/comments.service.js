(function () {
  'use strict';

  angular
    .module('app.issues')
    .factory('Comment', Comment);

  /* @ngInject */
  function Comment(DS, Repo, Issue) {
    return DS.defineResource({
      name: 'comment',
      endpoint: '/comments',
      relations: {
        belongsTo: {
          issue: {
            parent: true,
            localKey: 'issueNo',
            localField: 'issue'
          }
        }
      }
    });
  }
})();
