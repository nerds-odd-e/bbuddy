import {Inject} from '../../common/decorators'

@Inject('accountsModel')
export default class AccountsController{
    constructor(accounts) {
        accounts.fetchAll((data) => this.accounts = data)
    }
}

