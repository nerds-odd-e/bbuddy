export default class Authentication {
    constructor($q, $timeout){
        this.$q = $q
        this.$timeout = $timeout
        this.authenticatedUser = null
    }
    authenticate(username, password) {
        let {$q, $timeout} = this
        const checkCredential = () => $q((resolve, reject) => {
            var valid = username == 'user' && password == 'password'
            return valid ? resolve(username) : reject('Invalid username or password')
        })
        return $timeout(checkCredential, 800)
            .then((authenticatedUser) => {
                this.authenticatedUser = authenticatedUser
            })
    }
    isAuthenticated(){
        return !!this.authenticatedUser
    }
}

Authentication.$inject = ['$q', '$timeout']