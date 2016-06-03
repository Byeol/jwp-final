(function () {
  'use strict';

  angular
    .module('app.layouts')
    .controller('LayoutController', LayoutController);

  /* @ngInject */
  function LayoutController($scope, ngDialog, authService, Layout, User) {
    /* jshint validthis: true */
    const vm = this;

    vm.status = Layout.status;

    vm.openSignInDialog = () => {
      vm.error = null;
      ngDialog.open({
        template: 'app/layouts/sign-in.dialog.html',
        className: 'ngdialog-theme-default',
        scope: $scope
      });
    };

    vm.openSignUpDialog = () => {
      vm.error = null;
      ngDialog.open({
        template: 'app/layouts/sign-up.dialog.html',
        className: 'ngdialog-theme-default',
        scope: $scope
      });
    };

    vm.signIn = cb => {
      authService
        .login(vm.form.username, vm.form.password)
        .success(cb)
        .error(() => vm.error = 'Incorrect username or password.');
    };

    vm.signUp = cb => {
      let user = {
        username: vm.form.username,
        password: vm.form.password
      };

      User.create(user)
        .then(cb)
        .catch(err => alert(err.statusText));
    };
  }
})();
