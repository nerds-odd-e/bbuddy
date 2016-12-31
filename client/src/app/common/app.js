import 'bootstrap/dist/css/bootstrap.css'
import '../../sass/layout.css'
import angular from 'angular'
import uiRouter from 'angular-ui-router'
import header from './header.js'
import sidebar from './sidebar.js'


let app = {
    template: require('./app.html'),
    controller: class AppController{
            constructor() {
                this.name = 'BBuddy'
            }
        }
}

function routing($stateProvider) {
    $stateProvider
        .state('app', {
            redirectTo: 'dashboard',
            url: '/app',
            component: 'app',
        });
}
routing.$inject = ['$stateProvider']

export default angular
    .module('app', [uiRouter, header, sidebar])
    .component('app', app)
    .config(routing)
    .name
