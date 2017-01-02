import angular from 'angular'
import AccountsController from './accounts.controller'
let accounts = {
    template: require('./accounts.html'),
    controller: AccountsController
}

function routing($stateProvider) {
    $stateProvider
        .state('accounts', {
            parent: 'app',
            url: '/accounts',
            component: 'accounts',
            data:{
                requireAuth: true
            }
        });
}
routing.$inject = ['$stateProvider']

export default angular
    .module('accounts', [])
    .component('accounts', accounts)
    .config(routing)
    .name

