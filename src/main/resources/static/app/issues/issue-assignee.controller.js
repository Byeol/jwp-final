(function () {
  'use strict';

  angular
    .module('app.issues')
    .controller('IssueAssigneeController', IssueAssigneeController);

  /* @ngInject */
  function IssueAssigneeController(issue, collaborators, Layout, Issue) {
    /* jshint validthis: true */
    const vm = this;
    Layout.status.active = "issues";

    vm.issue = issue;
    vm.collaborators = collaborators;
    console.log(collaborators);

    vm.setAssignee = assignee => {
      let query = {
        repoId: 1,
      };

      let issue = {
        'assignee': assignee.username
      };

      let options = {
        params: query
      };

      Issue.update(vm.issue.number, issue, options)
        .then(issue => {
          console.log(issue);
        })
        .catch(err => alert(err.statusText));
    };
  }
})();
