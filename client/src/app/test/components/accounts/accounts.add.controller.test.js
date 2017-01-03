import Controller from '../../../components/accounts/accounts.add.controller';

describe('accounts add controller', function() {
    it('add an account successfully', function(){
        var accounts = {add: () => {}}, $state = {go: ()=>{}}
        var add = sinon.stub(accounts, 'add').yields()
        var go = sinon.spy($state, 'go')
        var controller = new Controller(accounts, $state)
        controller.account.name = 'AHA'
        controller.account.balanceBroughtForward = 100000

        controller.save()

        add.should.have.been.calledWith({name: 'AHA', balanceBroughtForward: 100000})
        go.should.have.been.calledWith('accounts')
    })

    it('add an account failed', function(){
        var accounts = {add: () => {}}, $state = {go: ()=>{}}
        var add = sinon.stub(accounts, 'add').callsArgWith(2, 'Error')
        var go = sinon.spy($state, 'go')
        var controller = new Controller(accounts, $state)
        controller.account.name = ''
        controller.account.balanceBroughtForward = 0

        controller.save()

        add.should.have.been.calledWith({name: '', balanceBroughtForward: 0})
        controller.message = 'Error'
    })
})
