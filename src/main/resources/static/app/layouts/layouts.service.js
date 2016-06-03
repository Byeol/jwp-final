(function () {
  'use strict';

  angular
    .module('app.layouts')
    .factory('Layout', Layout);

  /* @ngInject */
  function Layout(Issue, $filter) {
    let status = {
      active: null
    };

    init();

    return {
      status: status
    };

    function init() {
      let query = {
        repoId: 1
      };

      Issue.findAll(query, {
        cacheResponse: false
      }).then(issues => $filter('filter')(issues, { state: 'open' }))
        .then(issues => status.open = issues.length);
    }
  }
})();
