import angular from 'angular'
import {app, routing, http} from './common'
import router from 'angular-ui-router'
import components from './components'

var root = {
    template: require('./root.html')
}

export default angular
    .module('root', [ router, app, components ])
    .component('root', root)
    .config(routing)
    .config(http)
    .name

