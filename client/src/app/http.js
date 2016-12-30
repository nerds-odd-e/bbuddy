http.$inject = ['$httpProvider'];

export default function http($httpProvider) {
    $httpProvider.defaults.withCredentials = true;
}