import angular from 'angular'
import router from 'angular-ui-router'
import {common, routing, http} from './common'
import components from './components'

export default angular
    .module('root', [ router, common, components ])
    .component('root', { template: require('./root.html') })
    .config(routing)
    .config(http)
    .name

