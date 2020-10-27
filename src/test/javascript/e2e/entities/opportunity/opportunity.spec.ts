import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OpportunityComponentsPage, OpportunityDeleteDialog, OpportunityUpdatePage } from './opportunity.page-object';

const expect = chai.expect;

describe('Opportunity e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let opportunityComponentsPage: OpportunityComponentsPage;
  let opportunityUpdatePage: OpportunityUpdatePage;
  let opportunityDeleteDialog: OpportunityDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Opportunities', async () => {
    await navBarPage.goToEntity('opportunity');
    opportunityComponentsPage = new OpportunityComponentsPage();
    await browser.wait(ec.visibilityOf(opportunityComponentsPage.title), 5000);
    expect(await opportunityComponentsPage.getTitle()).to.eq('jhipsterSampleApplicationApp.opportunity.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(opportunityComponentsPage.entities), ec.visibilityOf(opportunityComponentsPage.noResult)),
      1000
    );
  });

  it('should load create Opportunity page', async () => {
    await opportunityComponentsPage.clickOnCreateButton();
    opportunityUpdatePage = new OpportunityUpdatePage();
    expect(await opportunityUpdatePage.getPageTitle()).to.eq('jhipsterSampleApplicationApp.opportunity.home.createOrEditLabel');
    await opportunityUpdatePage.cancel();
  });

  it('should create and save Opportunities', async () => {
    const nbButtonsBeforeCreate = await opportunityComponentsPage.countDeleteButtons();

    await opportunityComponentsPage.clickOnCreateButton();

    await promise.all([
      opportunityUpdatePage.setTimeOfMessageInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      opportunityUpdatePage.setOpportunityIdInput('5'),
      opportunityUpdatePage.setTypeInput('type'),
      opportunityUpdatePage.setHandicapInput('handicap'),
      opportunityUpdatePage.setLineInput('line'),
      opportunityUpdatePage.setSequenceInput('sequence'),
      opportunityUpdatePage.setTradingStatusInput('5'),
      opportunityUpdatePage.setActualTradingTimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      opportunityUpdatePage.setNoteInput('note'),
      opportunityUpdatePage.setBetStopInput('5'),
      opportunityUpdatePage.setResultsInput('results'),
      opportunityUpdatePage.matchSelectLastOption(),
    ]);

    expect(await opportunityUpdatePage.getTimeOfMessageInput()).to.contain(
      '2001-01-01T02:30',
      'Expected timeOfMessage value to be equals to 2000-12-31'
    );
    expect(await opportunityUpdatePage.getOpportunityIdInput()).to.eq('5', 'Expected opportunityId value to be equals to 5');
    expect(await opportunityUpdatePage.getTypeInput()).to.eq('type', 'Expected Type value to be equals to type');
    expect(await opportunityUpdatePage.getHandicapInput()).to.eq('handicap', 'Expected Handicap value to be equals to handicap');
    expect(await opportunityUpdatePage.getLineInput()).to.eq('line', 'Expected Line value to be equals to line');
    expect(await opportunityUpdatePage.getSequenceInput()).to.eq('sequence', 'Expected Sequence value to be equals to sequence');
    expect(await opportunityUpdatePage.getTradingStatusInput()).to.eq('5', 'Expected tradingStatus value to be equals to 5');
    expect(await opportunityUpdatePage.getActualTradingTimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected actualTradingTime value to be equals to 2000-12-31'
    );
    expect(await opportunityUpdatePage.getNoteInput()).to.eq('note', 'Expected Note value to be equals to note');
    expect(await opportunityUpdatePage.getBetStopInput()).to.eq('5', 'Expected betStop value to be equals to 5');
    expect(await opportunityUpdatePage.getResultsInput()).to.eq('results', 'Expected Results value to be equals to results');

    await opportunityUpdatePage.save();
    expect(await opportunityUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await opportunityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Opportunity', async () => {
    const nbButtonsBeforeDelete = await opportunityComponentsPage.countDeleteButtons();
    await opportunityComponentsPage.clickOnLastDeleteButton();

    opportunityDeleteDialog = new OpportunityDeleteDialog();
    expect(await opportunityDeleteDialog.getDialogTitle()).to.eq('jhipsterSampleApplicationApp.opportunity.delete.question');
    await opportunityDeleteDialog.clickOnConfirmButton();

    expect(await opportunityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
