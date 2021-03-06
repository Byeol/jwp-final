(function () {
  'use strict';

  angular
    .module('app.layouts')
    .factory('Layout', Layout);

  /* @ngInject */
  function Layout(Issue, $filter, Constant) {
    let status = {
      active: null,
      login: false,
      username: null
    };

    init();

    return {
      status: status
    };

    function init() {
      let query = {
        repoId: Constant.repoId
      };

      Issue.findAll(query, {
        cacheResponse: false
      }).then(issues => $filter('filter')(issues, { state: 'open' }))
        .then(issues => status.open = issues.length);
    }
  }
})();
