(function () {
  'use strict';

  angular
    .module('app.issues')
    .controller('IssueDetailController', IssueDetailController);

  /* @ngInject */
  function IssueDetailController(issue, Layout) {
    /* jshint validthis: true */
    const vm = this;
    Layout.status.active = "issues";

    vm.issue = issue;
  }
})();
