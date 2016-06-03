(function () {
  'use strict';

  angular
    .module('app.issues')
    .controller('IssueLabelsController', IssueLabelsController);

  /* @ngInject */
  function IssueLabelsController(issue, labels, Layout, IssueLabel, Constant) {
    /* jshint validthis: true */
    const vm = this;
    Layout.status.active = "issues";

    vm.issue = issue;
    vm.labels = labels;

    vm.addLabel = label => {
      let query = {
        repoId: Constant.repoId,
        issueNo: vm.issue.number
      };

      let issueLabel = [
        label.name
      ];

      let options = {
        params: query
      };

      IssueLabel.create(issueLabel, options)
        .catch(err => alert(err));
    };
  }
})();
