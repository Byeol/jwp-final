(function () {
  'use strict';

  angular
    .module('app.core')
    .config(config);

  function config(DSProvider, DSHttpAdapterProvider, Constant) {
    angular.extend(DSProvider.defaults, {
      beforeUpdate: (options, params, cb) => {
        options.method = 'patch';
        cb(null, params);
      }
    });

    angular.extend(DSHttpAdapterProvider.defaults, {
      basePath: Constant.baseUrl
    });
  }
})();
