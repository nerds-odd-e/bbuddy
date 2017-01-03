import angular from 'angular'
import list from './accounts'
import add from './accounts.add'

export default angular
    .module('accounts', [list, add])
    .name