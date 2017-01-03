import Controller from '../../../components/accounts/list.controller';

describe('accounts controller', function() {
    it('Show all accounts', function(){
        var accounts = {fetchAll: () => {}}
        sinon.stub(accounts, 'fetchAll').yields([{id: 1, name: 'ICBC', balanceBroughtForward: 1000}])
        var controller = new Controller(accounts)
        controller.accounts.should.eql([{id: 1, name: 'ICBC', balanceBroughtForward: 1000}])
    })
})
