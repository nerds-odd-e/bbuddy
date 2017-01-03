import angular from 'angular'

export default angular
    .module('app.sidebar', [])
    .component('sidebar', { template: require('./sidebar.html') })
    .name;