(function () {
  'use strict';

  angular
    .module('app.issues')
    .controller('IssuesController', IssuesController);

  /* @ngInject */
  function IssuesController(issues, $scope, $state, ngDialog, Layout, Issue) {
    /* jshint validthis: true */
    const vm = this;
    Layout.status.active = "issues";

    vm.issues = issues;

    vm.openCreateDialog = () => ngDialog.open({
      template: 'app/issues/issue-create.dialog.html',
      className: 'ngdialog-theme-default',
      scope: $scope
    });

    vm.submit = cb => {
      let query = {
        repoId: 1
      };

      let issue = {
        'title': vm.form.title,
        'body': vm.form.body
      };

      let options = {
        params: query
      };

      Issue.create(issue, options)
        .then(issue => $state.go('issue-detail', { number: issue.number }))
        .then(cb)
        .catch(err => alert(err.statusText));
    };
  }
})();
