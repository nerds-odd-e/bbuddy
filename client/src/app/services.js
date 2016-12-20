import angular from 'angular'
import Authentication from './authentication'

export default angular.module("services", [])
    .service("Authentication", Authentication)