export default class Authentication {
    constructor($http){
        this.authenticated= false
        this.$http = $http
    }
    authenticate(credential, success, fail) {
        this.$http.post("http://localhost:8090/signin",
            `username=${credential.username}&password=${credential.password}`,
            {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}
        ).then((response) => {
            this.authenticated = !response.data.includes("Invalid username and password!")
            this.authenticated ? success() : fail()
        })
    }
    isAuthenticated(){
        return !!this.authenticated
    }
}

Authentication.$inject = ['$http']