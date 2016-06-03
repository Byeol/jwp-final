(function () {
  'use strict';

  angular
    .module('app.issues')
    .run(appRun);

  /* @ngInject */
  function appRun(routerHelper) {
    routerHelper.configureStates(getStates(), '/issues');
  }

  function getStates() {
    return [
      {
        state: 'issues',
        config: {
          url: '/issues',
          parent: 'root',
          views: {
            "@": {
              templateUrl: 'app/issues/issues.html',
              controller: 'IssuesController as vm'
            }
          },
          resolve: {
            issues: getIssues
          }
        }
      },
      {
        state: 'issue-detail',
        config: {
          url: '/issues/:number',
          parent: 'root',
          views: {
            "@": {
              templateUrl: 'app/issues/issue-detail.html',
              controller: 'IssueDetailController as vm'
            },
            "comments@issue-detail": {
              templateUrl: 'app/issues/comments.html',
              controller: 'CommentsController as vm'
            },
            "issue-labels@issue-detail": {
              templateUrl: 'app/issues/issue-labels.html',
              controller: 'IssueLabelsController as vm'
            },
            "issue-milestone@issue-detail": {
              templateUrl: 'app/issues/issue-milestone.html',
              controller: 'IssueMilestoneController as vm'
            },
            "issue-assignee@issue-detail": {
              templateUrl: 'app/issues/issue-assignee.html',
              controller: 'IssueAssigneeController as vm'
            }
          },
          resolve: {
            issue: getIssue,
            comments: getComments,
            labels: getLabels,
            milestones: getMilestones,
            collaborators: getCollaborators
          }
        }
      }
    ];
  }

  function getIssues(Issue, Constant) {
    let query = {
      repoId: Constant.repoId
    };

    return Issue.findAll(query, {
      cacheResponse: false
    });
  }

  function getIssue($stateParams, Issue, Constant) {
    let issueNo = $stateParams.number;

    return Issue.find(issueNo, {
      bypassCache: true,
      params: {
        repoId: Constant.repoId
      }
    });
  }

  function getComments($stateParams, Comment, Constant) {
    let issueNo = $stateParams.number;
    let query = {
      repoId: Constant.repoId,
      issueNo: issueNo
    };

    return Comment.findAll(query, {
      cacheResponse: false
    });
  }

  function getLabels(Label, Constant) {
    let query = {
      repoId: Constant.repoId
    };

    return Label.findAll(query, {
      cacheResponse: false
    });
  }

  function getMilestones(Milestone, Constant) {
    let query = {
      repoId: Constant.repoId
    };

    return Milestone.findAll(query, {
      cacheResponse: false
    });
  }

  function getCollaborators(Collaborator, Constant) {
    let query = {
      repoId: Constant.repoId
    };

    return Collaborator.findAll(query, {
      cacheResponse: false
    });
  }
})();
