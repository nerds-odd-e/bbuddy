import angular from 'angular'
import list from './list.component'
import add from './add.component'

export default angular
    .module('accounts', [list, add])
    .name