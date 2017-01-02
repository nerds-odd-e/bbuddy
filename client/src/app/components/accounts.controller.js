import {Inject} from '../common/decorators'

@Inject('$http')
export default class AccountsController{
    constructor($http) {
        $http.get("http://localhost:8090/accounts/list.json").success((data)=>{
            this.accounts = data;
        })
    }
}

