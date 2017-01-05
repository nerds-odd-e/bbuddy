import 'bootstrap/dist/css/bootstrap.css'
import 'angular-loading-bar/build/loading-bar.css'
import '../../../sass/layout.css'
import angular from 'angular'
import loading from 'angular-loading-bar'
import animate from 'angular-animate'
import uiRouter from 'angular-ui-router'
import header from './header'
import sidebar from './sidebar'


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
    .module('app', [uiRouter, loading, header, sidebar])
    .component('app', app)
    .config(routing)
    .run(($transitions, cfpLoadingBar)=>{
        $transitions.onStart({}, cfpLoadingBar.start)
        $transitions.onSuccess({}, cfpLoadingBar.complete)
    })
    .name
