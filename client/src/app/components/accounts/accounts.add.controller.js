import {Inject} from '../../common/decorators'

@Inject('api')
export default class AccountsAddController {
    constructor(api){
        this.api = api
        this.account = {
            name: '',
            balanceBroughtForward: 0
        }
        this.message = ""
    }
    save(){
        this.api.accounts.add(this.account, (result) => {
            result.success ? $state.go('accounts') : this.message = result.message
        })
    }
}