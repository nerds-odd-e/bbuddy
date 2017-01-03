import {Inject} from '../../common/decorators'

@Inject('api')
export default class Accounts {
    constructor(api){
        this.api = api
    }
    fetchAll(callback){
        this.api.accounts.all((response)=> callback(response.data))
    }
}