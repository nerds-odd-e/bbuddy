import angular from 'angular'

class AccountsController{
    constructor($http) {
        var self = this
        $http.get("http://localhost:8090/accounts/list.json").success((data)=>{
            self.accounts = data;
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

