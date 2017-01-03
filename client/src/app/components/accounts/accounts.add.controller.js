import {Inject} from '../../common/decorators'

@Inject('api', '$state')
export default class AccountsAddController {
    constructor(api, $state){
        this.api = api
        this.account = {
            name: '',
            balanceBroughtForward: 0
        }
        this.message = ""
        this.$state = $state
    }
    save(){
        this.api.accounts.add(this.account, (result) => {
            result.data.success ? this.$state.go('accounts') : this.message = result.data.errors[0].defaultMessage
        })
    }
}