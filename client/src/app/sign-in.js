import angular from 'angular'
import authentication from './authentication'

class SignInController {
    constructor(authentication, $state){
        this.credential = {
            username: '',
            password: ''
        }
        this.authentication = authentication
        this.$state = $state
    }
    signIn(credential) {
        let {authentication, $state} = this
        authentication.authenticate(credential.username, credential.password)
            .then(() => {
                $state.go('dashboard')
            })
    }
}
SignInController.$inject = ['authentication', '$state']

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
            url: '/auth',
            template: '<div ui-view></div>'
        })
        .state('auth.signIn', {
            url: '/signin',
            component: 'signIn'
        });
    $urlRouterProvider.otherwise('/auth/signin');
}
routing.$inject = ['$stateProvider', '$urlRouterProvider']

function authenticating($transitions, $state, authentication){
    $transitions.onStart({
        to: function (state) {
            return !!(state.data && state.data.requireAuth);
        }
    }, function() {
        if (!authentication.isAuthenticated()){
            return $state.target('auth.signIn')
        }
    });
}
authenticating.$inject = ['$transitions', '$state', 'authentication']

export default angular
    .module('auth', [])
    .component('signIn', signIn)
    .service('authentication', authentication)
    .config(routing)
    .run(authenticating)
    .name