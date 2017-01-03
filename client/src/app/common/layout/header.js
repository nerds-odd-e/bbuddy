import angular from 'angular'

var header = {
    bindings: {
        user: '<',
        onSignOut: '&'
    },
    template: require('./header.html')
};

export default angular
    .module('app.header', [])
    .component('header', header)
    .name;