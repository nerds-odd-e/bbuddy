import {Inject} from '../../common/decorators'


@Inject('api')
export default class Authentication {
    constructor(api){
        this.authenticated= false
        this.api = api
    }
    authenticate(credential, success=()=>{}, fail=()=>{}) {
        this.api.auth.signIn(credential,
            (response) => {
                this.authenticated = !response.data.includes("Invalid username and password!")
                this.authenticated ? success() : fail()
        })
    }
    isAuthenticated(){
        return !!this.authenticated
    }
}
