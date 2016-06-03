(function () {
  'use strict';

  angular
    .module('app.issues')
    .controller('IssueMilestoneController', IssueMilestoneController);

  /* @ngInject */
  function IssueMilestoneController(issue, milestones, Layout, Issue) {
    /* jshint validthis: true */
    const vm = this;
    Layout.status.active = "issues";

    vm.issue = issue;
    vm.milestones = milestones;

    vm.setMilestone = milestone => {
      let query = {
        repoId: 1,
      };

      let issue = {
        'milestone': milestone.number
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
