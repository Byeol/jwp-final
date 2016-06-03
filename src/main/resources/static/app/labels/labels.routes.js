(function () {
  'use strict';

  angular
    .module('app.labels')
    .run(appRun);

  /* @ngInject */
  function appRun(routerHelper) {
    routerHelper.configureStates(getStates(), '/labels');
  }

  function getStates() {
    return [
      {
        state: 'labels',
        config: {
          url: '/labels',
          parent: 'root',
          views: {
            "@": {
              templateUrl: 'app/labels/labels.html',
              controller: 'LabelsController as vm'
            }
          },
          resolve: {
            labels: getLabels
          }
        }
      },
      {
        state: 'label-detail',
        config: {
          url: '/labels/:name',
          parent: 'root',
          views: {
            "@": {
              templateUrl: 'app/labels/label-detail.html',
              controller: 'LabelDetailController as vm'
            }
          },
          resolve: {
            label: getLabel
          }
        }
      }
    ];
  }

  function getLabels(Label) {
    let query = {
      repoId: 1
    };

    return Label.findAll(query, {
      cacheResponse: false
    });
  }

  function getLabel($stateParams, Label) {
    let labelName = $stateParams.name;

    return Label.find(labelName, {
      bypassCache: true,
      params: {
        repoId: 1
      }
    });
  }
})();
