(function () {
  'use strict';

  angular
    .module('app.issues')
    .controller('IssueLabelsController', IssueLabelsController);

  /* @ngInject */
  function IssueLabelsController(issue, labels, Layout, IssueLabel) {
    /* jshint validthis: true */
    const vm = this;
    Layout.status.active = "issues";

    vm.issue = issue;
    vm.labels = labels;

    vm.addLabel = label => {
      let query = {
        repoId: 1,
        issueNo: vm.issue.number
      };

      let issueLabel = [
        label.name
      ];

      let options = {
        params: query
      };

      IssueLabel.create(issueLabel, options)
        .then(issueLabel => {
          console.log(issueLabel);
        })
        .catch(err => alert(err));
    };
  }
})();
