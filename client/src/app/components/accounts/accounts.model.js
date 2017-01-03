import {Inject} from '../../common/decorators'

@Inject('api')
export default class Accounts {
    constructor(api){
        this.api = api
    }
    fetchAll(callback){
        this.api.accounts.all((response)=> callback(response.data))
    }
    add(account, success, failure){
        this.api.accounts.add(account,
            (response) => {
                let result = response.data
                result.success ? success() : failure(result.message)
            }
        )
    }
}