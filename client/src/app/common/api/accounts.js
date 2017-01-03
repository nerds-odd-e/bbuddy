import {Inject} from '../decorators'

@Inject('http')
export default class Accounts {
    constructor(http){
        this.http = http
    }
    all(callback){
        this.http.get("accounts/list.json", callback)
    }
    add(account, callback){
        this.http.post("accounts/add.json", account, callback)
    }
}
