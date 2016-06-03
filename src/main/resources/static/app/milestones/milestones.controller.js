(function () {
  'use strict';

  angular
    .module('app.milestones')
    .controller('MilestonesController', MilestonesController);

  /* @ngInject */
  function MilestonesController(milestones, $scope, $state, ngDialog, Layout, Milestone) {
    /* jshint validthis: true */
    const vm = this;
    Layout.status.active = "milestones";

    vm.milestones = milestones;

    vm.openCreateDialog = () => ngDialog.open({
      template: 'app/milestones/milestone-create.dialog.html',
      className: 'ngdialog-theme-default',
      scope: $scope
    });

    vm.submit = cb => {
      let query = {
        repoId: 1
      };

      let milestone = {
        'title': vm.form.title,
        'description': vm.form.description
      };

      let options = {
        params: query
      };

      Milestone.create(milestone, options)
        .then(milestone => $state.go('milestone-detail', { number: milestone.number }))
        .then(cb)
        .catch(err => alert(err.statusText));
    };
  }
})();
