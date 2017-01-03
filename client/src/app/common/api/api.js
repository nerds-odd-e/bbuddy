import {Inject} from '../decorators'

@Inject('authApi', 'accountsApi')
export default class Api {
    constructor(auth, accounts){
        this.auth = auth
        this.accounts = accounts
    }
}