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
        if (account.name.trim().length == 0){
            failure('Account name should not be empty!')
            return
        }
        this.api.accounts.add(account,
            (response) => {
                let result = response.data
                result.success ? success() : failure(result.errors[0].defaultMessage)
            }
        )
    }
}