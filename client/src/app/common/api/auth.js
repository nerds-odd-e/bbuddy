import {Inject} from '../decorators'

@Inject('http')
export default class Auth {
    constructor(http){
        this.http = http
    }
    signIn(credential, callback){
        this.http.post("signin",
            `username=${credential.username}&password=${credential.password}`,
            {headers: {'Content-Type': 'application/x-www-form-urlencoded'}},
            callback
        )
    }
}
