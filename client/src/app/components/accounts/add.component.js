import angular from 'angular'
import AccountsAddController from './add.controller'
import Accounts from './accounts.model'

function routing($stateProvider) {
    $stateProvider
        .state('accountsAdd', {
            parent: 'app',
            url: '/accounts/add',
            component: 'accountsAdd',
            data:{
                requireAuth: true
            }
        });
}
routing.$inject = ['$stateProvider']

export default angular
    .module('accounts.add', [])
    .component('accountsAdd', {
        template: require('./add.html'),
        controller: AccountsAddController
    })
    .service('accountsModel', Accounts)
    .config(routing)
    .name

