import angular from 'angular'
import common from './app.js'
import dashboard from './dashboard.js'
import router from 'angular-ui-router'
import routing from './routing'

var root = {
    template: require('./root.html')
}

export default angular
    .module('root', [ router, common, dashboard ])
    .component('root', root)
    .config(routing)
    .name

