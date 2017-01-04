import Accounts from '../../../components/accounts/accounts.model';

describe('accounts model', function() {
    var account = {name: 'AHA', balanceBroughtForward: 100000}
    var api, add, accounts, success, failure
    beforeEach(() => {
        api = {accounts: {add: () => {}}}
        add = sinon.stub(api.accounts, 'add').yields({success: true, errors: []})
        accounts = new Accounts(api)
        success = sinon.spy()
        failure = sinon.spy()
    })
    it('add an account successfully', function(){
        accounts.add(account, success, failure)

        add.should.have.been.calledWith({name: 'AHA', balanceBroughtForward: 100000})
        success.should.have.been.called
    })

    it('server response error when adding an account', function(){
        add.yields({success: false, errors: [{defaultMessage: 'Error'}]})

        accounts.add(account, success, failure)

        failure.should.have.been.calledWith('Error')
    })
    it('account name should not be empty when adding an account', function(){
        account.name = ''

        accounts.add(account, success, failure)

        add.should.not.have.been.called
        failure.should.have.been.calledWith('Account name should not be empty!')
    })
    it('account name should not be filled with blanks when adding an account', function(){
        account.name = '  '

        accounts.add(account, success, failure)

        add.should.not.have.been.called
        failure.should.have.been.calledWith('Account name should not be empty!')
    })
})
