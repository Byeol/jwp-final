(function () {
  'use strict';

  angular
    .module('app.milestones')
    .run(appRun);

  /* @ngInject */
  function appRun(routerHelper) {
    routerHelper.configureStates(getStates(), '/milestones');
  }

  function getStates() {
    return [
      {
        state: 'milestones',
        config: {
          url: '/milestones',
          parent: 'root',
          views: {
            "@": {
              templateUrl: 'app/milestones/milestones.html',
              controller: 'MilestonesController as vm'
            }
          },
          resolve: {
            milestones: getMilestones
          }
        }
      },
      {
        state: 'milestone-detail',
        config: {
          url: '/milestones/:number',
          parent: 'root',
          views: {
            "@": {
              templateUrl: 'app/milestones/milestone-detail.html',
              controller: 'MilestoneDetailController as vm'
            }
          },
          resolve: {
            milestone: getMilestone
          }
        }
      }
    ];
  }

  function getMilestones(Milestone) {
    let query = {
      repoId: 1
    };

    return Milestone.findAll(query, {
      cacheResponse: false
    });
  }

  function getMilestone($stateParams, Milestone) {
    let number = $stateParams.number;

    return Milestone.find(number, {
      bypassCache: true,
      params: {
        repoId: 1
      }
    });
  }
})();
