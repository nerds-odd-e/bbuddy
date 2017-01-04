import {Inject} from '../decorators'

@Inject('$http')
export default class Http{
    constructor($http) {
        this.$http = $http
        this.hostUrl = 'http://localhost:8090/'
    }
    url(path) {
        return `${this.hostUrl}${path}`
    }
    callbackWithResponseData(callback){
        return (response) => callback(response.data)
    }
    get(path, callback) {
        this.$http.get(this.url(path)).then(this.callbackWithResponseData(callback))
    }
    post(path, data, config, callback){
        if (typeof config === 'function'){
            callback = config
            config = {}
        }
        this.$http.post(this.url(path), data, config).then(this.callbackWithResponseData(callback), this.callbackWithResponseData(callback))
    }
}

