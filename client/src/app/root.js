import angular from 'angular'
import common from './app.js'
import dashboard from './dashboard.js'
import auth from './sign-in.js'
import router from 'angular-ui-router'
import routing from './routing'
import http from './http'
import accounts from './accounts'

var root = {
    template: require('./root.html')
}

export default angular
    .module('root', [ router, common, dashboard, auth, accounts ])
    .component('root', root)
    .config(routing)
    .config(http)
    .name

