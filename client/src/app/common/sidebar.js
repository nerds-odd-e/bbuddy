import angular from 'angular'

var sidebar = {
    template: require('./sidebar.html')
};

export default angular
    .module('app.sidebar', [])
    .component('sidebar', sidebar).name;