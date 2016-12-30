export default class Authentication {
    constructor($http){
        this.authenticated= false
        this.$http = $http
    }
    authenticate(username, password) {
        let self = this
        return this.$http.post("http://localhost:8090/signin",
            `username=${username}&password=${password}`,
            {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}
        ).then(() => {
                self.authenticated = true
            })
    }
    isAuthenticated(){
        return !!this.authenticated
    }
}

Authentication.$inject = ['$http']