(function () {
  'use strict';

  angular
    .module('app.issues')
    .factory('IssueLabel', IssueLabel);

  /* @ngInject */
  function IssueLabel(DS, Repo, Issue) {
    return DS.defineResource({
      name: 'issueLabel',
      endpoint: '/labels',
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
