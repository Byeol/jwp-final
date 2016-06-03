(function () {
  'use strict';

  angular
    .module('app.issues')
    .controller('CommentsController', CommentsController);

  /* @ngInject */
  function CommentsController(issue, comments, $scope, $state, Layout, Comment) {
    /* jshint validthis: true */
    const vm = this;

    vm.comments = comments;

    vm.submit = cb => {
      let query = {
        repoId: 1,
        issueNo: issue.number
      };

      let comment = {
        'body': vm.form.body
      };

      let options = {
        params: query
      };

      Comment.create(comment, options)
        .then(comment => vm.comments.push(comment))
        .then(cb)
        .catch(err => alert(err.statusText));
    };

    vm.clear = () => {
      vm.form.body = "";
    };
  }
})();
