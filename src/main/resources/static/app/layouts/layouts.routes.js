(function () {
  'use strict';

  angular
    .module('app.layouts')
    .run(appRun);

  /* @ngInject */
  function appRun(routerHelper) {
    routerHelper.configureStates(getStates());
  }

  function getStates() {
    return [
      {
        state: 'root',
        config: {
          views: {
            'navbar@': {
              templateUrl: 'app/layouts/navbar.html',
              controller: 'LayoutController as vm'
            }
          }
        }
      }
    ];
  }
})();
