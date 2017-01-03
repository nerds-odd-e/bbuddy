import {Inject} from '../../common/decorators'

@Inject('accountsModel', '$state')
export default class AccountsAddController {
    constructor(accounts, $state){
        this.accounts = accounts
        this.$state = $state
        this.account = {
            name: '',
            balanceBroughtForward: 0
        }
        this.message = ""
    }
    save(){
        this.accounts.add(this.account,
            () => this.$state.go('accounts'),
            (message) => this.message = message )
    }
}