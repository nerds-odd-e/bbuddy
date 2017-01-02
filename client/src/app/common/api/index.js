import angular from 'angular'
import http from './http'
import api from './api'
import auth from './auth'


export default angular
    .module('api', [])
    .service('http', http)
    .service('api', api)
    .service('auth', auth)
    .name
