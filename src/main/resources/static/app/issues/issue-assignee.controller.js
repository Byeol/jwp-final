(function () {
  'use strict';

  angular
    .module('app.issues')
    .controller('IssueAssigneeController', IssueAssigneeController);

  /* @ngInject */
  function IssueAssigneeController(issue, collaborators, Layout, Issue, Constant) {
    /* jshint validthis: true */
    const vm = this;
    Layout.status.active = "issues";

    vm.issue = issue;
    vm.collaborators = collaborators;
    console.log(collaborators);

    vm.setAssignee = assignee => {
      let query = {
        repoId: Constant.repoId,
      };

      let issue = {
        'assignee': assignee.username
      };

      let options = {
        params: query
      };

      Issue.update(vm.issue.number, issue, options)
        .catch(err => alert(err.statusText));
    };
  }
})();
