import angular from 'angular'
import {Inject} from '../common/decorators'

@Inject('$http')
class AccountsController{
    constructor($http) {
        $http.get("http://localhost:8090/accounts/list.json").success((data)=>{
            this.accounts = data;
        })
    }
}

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

