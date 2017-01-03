import Accounts from '../../../components/accounts/accounts.model';

describe('accounts model', function() {
    it('add an account successfully', function(){
        var api = {accounts: {add: () => {}}}
        var add = sinon.stub(api.accounts, 'add').yields({data: {success: true, message: ''}})
        var accounts = new Accounts(api)
        var account = {name: 'AHA', balanceBroughtForward: 100000}
        var success = sinon.spy()
        var failure = sinon.spy()

        accounts.add(account, success, failure)

        add.should.have.been.calledWith({name: 'AHA', balanceBroughtForward: 100000})
        success.called.should.be.true
    })

    it('server response error when adding an account', function(){
        var api = {accounts: {add: () => {}}}
        var add = sinon.stub(api.accounts, 'add').yields({data: {success: false, message: 'Error'}})
        var accounts = new Accounts(api)
        var account = {name: 'AHA', balanceBroughtForward: 100000}
        var success = sinon.spy()
        var failure = sinon.spy()

        accounts.add(account, success, failure)

        failure.calledWith('Error').should.be.true
    })
})
