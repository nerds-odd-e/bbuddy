import angular from 'angular'
import account from './accounts'
import dashboard from './dashboard'
import signIn from './sign-in'

export default angular.module('components', [account, dashboard, signIn]).name