import angular from 'angular'
import signIn from './sign-in'

export default angular
    .module('auth', [signIn])
    .name