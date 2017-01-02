import {Inject} from '../decorators'

@Inject('auth')
export default class Api {
    constructor(auth){
        this.auth = auth
    }
}