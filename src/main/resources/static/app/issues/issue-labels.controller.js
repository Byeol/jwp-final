(function () {
  'use strict';

  angular
    .module('app.issues')
    .controller('IssueLabelsController', IssueLabelsController);

  /* @ngInject */
  function IssueLabelsController(issue, labels, Layout, Issue, Constant) {
    /* jshint validthis: true */
    const vm = this;
    Layout.status.active = "issues";

    vm.issue = issue;
    vm.labels = labels;

    vm.addLabel = label => {
      let query = {
        repoId: Constant.repoId,
      };

      let issue = {
        'labels': label.name
      };

      let options = {
        params: query
      };

      Issue.update(vm.issue.number, issue, options)
        .catch(err => alert(err.statusText));
    };
  }
})();
