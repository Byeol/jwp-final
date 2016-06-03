(function () {
  'use strict';

  angular
    .module('app.milestones')
    .controller('MilestoneDetailController', MilestoneDetailController);

  /* @ngInject */
  function MilestoneDetailController(milestone, Layout) {
    /* jshint validthis: true */
    const vm = this;
    Layout.status.active = "milestones";

    vm.milestone = milestone;
  }
})();
