import angular from 'angular'
import http from './http'
import api from './api'
import auth from './auth'
import accounts from './accounts'

export default angular
    .module('api', [])
    .service('http', http)
    .service('api', api)
    .service('authApi', auth)
    .service('accountsApi', accounts)
    .name
