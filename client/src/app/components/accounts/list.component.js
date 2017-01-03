import angular from 'angular'
import AccountsController from './list.controller'
import Accounts from './accounts.model'

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
    .module('accounts.list', [])
    .component('accounts', {
        template: require('./list.html'),
        controller: AccountsController
    })
    .service('accountsModel', Accounts)
    .config(routing)
    .name

