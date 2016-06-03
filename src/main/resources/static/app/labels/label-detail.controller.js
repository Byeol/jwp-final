(function () {
  'use strict';

  angular
    .module('app.labels')
    .controller('LabelDetailController', LabelDetailController);

  /* @ngInject */
  function LabelDetailController(label, Layout) {
    /* jshint validthis: true */
    const vm = this;
    Layout.status.active = "labels";

    vm.label = label;
  }
})();
