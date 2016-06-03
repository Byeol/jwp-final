(function () {
  'use strict';

  angular
    .module('app.issues')
    .controller('CommentsController', CommentsController);

  /* @ngInject */
  function CommentsController(issue, comments, $scope, $state, $http, Layout, Comment, Constant) {
    /* jshint validthis: true */
    const vm = this;

    vm.comments = comments;

    vm.submit = cb => {
      let query = {
        repoId: Constant.repoId,
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

    vm.fileSubmit = files => {
      let baseUrl = `${Constant.baseUrl}/repos/${Constant.repoId}/files`;
      let formData = new FormData();
      formData.append('file', files[0]);
      $http.post(baseUrl, formData, {
        transformRequest: angular.identity,
        headers: { 'Content-Type': undefined }
      }).then(({data}) => `${baseUrl}/${data.id}/${data.originalFilename}`)
        .then(url => vm.form.body = vm.form.body ? vm.form.body + ' ' + url : url)
        .catch(({statusText}) => alert(statusText));
    };

    vm.getFile = () => {
      document.querySelector("#comment-file-input").click();
    }

    vm.clear = () => {
      vm.form.body = "";
    };
  }
})();
