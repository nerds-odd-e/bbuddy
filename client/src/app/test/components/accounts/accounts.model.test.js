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

        add.calledWith({name: 'AHA', balanceBroughtForward: 100000}).should.be.true
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
    it('account name should not be empty when adding an account', function(){
        var api = {accounts: {add: () => {}}}
        var add = sinon.stub(api.accounts, 'add').yields({data: {success: false, message: 'Error'}})
        var accounts = new Accounts(api)
        var account = {name: '', balanceBroughtForward: 100000}
        var success = sinon.spy()
        var failure = sinon.spy()

        accounts.add(account, success, failure)

        add.called.should.be.false
        failure.calledWith('Account name should not be empty!').should.be.true
    })
    it('account name should not be filled with blanks when adding an account', function(){
        var api = {accounts: {add: () => {}}}
        var add = sinon.stub(api.accounts, 'add').yields({data: {success: false, message: 'Error'}})
        var accounts = new Accounts(api)
        var account = {name: '   ', balanceBroughtForward: 100000}
        var success = sinon.spy()
        var failure = sinon.spy()

        accounts.add(account, success, failure)

        add.called.should.be.false
        failure.calledWith('Account name should not be empty!').should.be.true
    })
})
