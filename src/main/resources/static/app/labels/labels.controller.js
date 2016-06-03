(function () {
  'use strict';

  angular
    .module('app.labels')
    .controller('LabelsController', LabelsController);

  /* @ngInject */
  function LabelsController(labels, $scope, $state, ngDialog, Layout, Label, Constant) {
    /* jshint validthis: true */
    const vm = this;
    Layout.status.active = "labels";

    vm.labels = labels;

    vm.openCreateDialog = () => ngDialog.open({
      template: 'app/labels/label-create.dialog.html',
      className: 'ngdialog-theme-default',
      scope: $scope
    });

    vm.submit = cb => {
      let query = {
        repoId: Constant.repoId
      };

      let label = {
        'name': vm.form.name,
        'color': vm.form.color
      };

      let options = {
        params: query
      };

      Label.create(label, options)
        .then(label => $state.go('label-detail', { name: label.name }))
        .then(cb)
        .catch(err => alert(err.statusText));
    };
  }
})();
