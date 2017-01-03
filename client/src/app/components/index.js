import angular from 'angular'
import accounts from './accounts'
import dashboard from './dashboard'
import auth from './auth'

export default angular
    .module('components', [accounts, dashboard, auth])
    .name