import {Inject} from '../../common/decorators'

@Inject('api')
export default class Accounts {
    constructor(api){
        this.api = api
    }
    fetchAll(callback){
        this.api.accounts.all(callback)
    }
    add(account, success, failure){
        if (account.name.trim().length == 0){
            failure('Account name should not be empty!')
            return
        }
        this.api.accounts.add(account,
            (result) => {
                result.success ? success() : failure(result.errors[0].defaultMessage)
            }
        )
    }
}