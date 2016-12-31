import angular from 'angular'
import {app, routing, http} from './common'
import router from 'angular-ui-router'
import components from './components'

export default angular
    .module('root', [ router, app, components ])
    .component('root', { template: require('./root.html') })
    .config(routing)
    .config(http)
    .name

