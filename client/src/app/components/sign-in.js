import angular from 'angular'
import authentication from './authentication'
import {Inject} from '../common/decorators'

@Inject('authentication', '$state')
class SignInController {
    constructor(authentication, $state){
        this.credential = {
            username: '',
            password: ''
        }
        this.message = ""
        this.authentication = authentication
        this.$state = $state
    }
    signIn() {
        let {authentication, $state, credential} = this
        authentication.authenticate(credential,
            () => {
                $state.go('dashboard')
            }, () => {
                this.message = "Invalid username and password."
            })
    }
}

let signIn = {
    bindings: {
        credential: '<'
    },
    template: require('./sign-in.html'),
    controller: SignInController
}

function routing($stateProvider, $urlRouterProvider){
    $stateProvider
        .state('auth', {
            redirectTo: 'auth.signIn',
            url: '/app',
            template: '<div ui-view></div>'
        })
        .state('auth.signIn', {
            url: '/signin',
            component: 'signIn'
        });
    $urlRouterProvider.otherwise('/app/signin');
}
routing.$inject = ['$stateProvider', '$urlRouterProvider']

function authenticating($transitions, $state, authentication){
    $transitions.onStart({
        to: function (state) {
            return !!(state.data && state.data.requireAuth)
        }
    }, function() {
        if (!authentication.isAuthenticated()){
            return $state.target('auth.signIn')
        }
    })
}
authenticating.$inject = ['$transitions', '$state', 'authentication']

export default angular
    .module('auth', [])
    .component('signIn', signIn)
    .service('authentication', authentication)
    .config(routing)
    .run(authenticating)
    .name