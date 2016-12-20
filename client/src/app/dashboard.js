import angular from 'angular'


let dashboard = {
        template: require('./dashboard.html'),
        controller: class DashboardController{
            constructor() {
                this.name = 'BBuddy'
            }
        }
    }

function routing($stateProvider) {
    $stateProvider
        .state('dashboard', {
            parent: 'app',
            url: '/dashboard',
            component: 'dashboard',
            data:{
                requireAuth: true
            }
        });
}
routing.$inject = ['$stateProvider']

export default angular
    .module('dashboard', [])
    .component('dashboard', dashboard)
    .config(routing)
    .name

