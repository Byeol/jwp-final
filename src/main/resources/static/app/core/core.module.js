(function () {
  'use strict';

  angular.module('app.core', [
    'ngResource',
    'ngDialog',
    'blocks.router',
    'js-data',
    'angularBasicAuth'
  ]);

  angular.module('app')
    .config(function (DSProvider, DSHttpAdapterProvider) {
      angular.extend(DSProvider.defaults, {
        beforeUpdate: (options, params, cb) => {
          options.method = 'patch';
          cb(null, params);
        }
      });

      angular.extend(DSHttpAdapterProvider.defaults, {
        basePath: "/"
      });
    });
})();
