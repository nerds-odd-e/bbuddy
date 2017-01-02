import Controller from '../../components/accounts.controller';

describe('accounts controller', function() {
    it('Show all accounts', function(){
        var $http = {get: () => {}}, q = {success: ()=>{}}
        sinon.stub($http, 'get').returns(q)
        var controller = new Controller($http)
    })
})
