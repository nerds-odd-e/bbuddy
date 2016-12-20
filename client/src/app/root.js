import angular from 'angular'
import common from './app.js'
import dashboard from './dashboard.js'
import auth from './sign-in.js'
import router from 'angular-ui-router'
import routing from './routing'

var root = {
    template: require('./root.html')
}

export default angular
    .module('root', [ router, common, dashboard, auth ])
    .component('root', root)
    .config(routing)
    .name

