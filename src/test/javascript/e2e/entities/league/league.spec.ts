import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { LeagueComponentsPage, LeagueDeleteDialog, LeagueUpdatePage } from './league.page-object';

const expect = chai.expect;

describe('League e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let leagueComponentsPage: LeagueComponentsPage;
  let leagueUpdatePage: LeagueUpdatePage;
  let leagueDeleteDialog: LeagueDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Leagues', async () => {
    await navBarPage.goToEntity('league');
    leagueComponentsPage = new LeagueComponentsPage();
    await browser.wait(ec.visibilityOf(leagueComponentsPage.title), 5000);
    expect(await leagueComponentsPage.getTitle()).to.eq('jhipsterSampleApplicationApp.league.home.title');
    await browser.wait(ec.or(ec.visibilityOf(leagueComponentsPage.entities), ec.visibilityOf(leagueComponentsPage.noResult)), 1000);
  });

  it('should load create League page', async () => {
    await leagueComponentsPage.clickOnCreateButton();
    leagueUpdatePage = new LeagueUpdatePage();
    expect(await leagueUpdatePage.getPageTitle()).to.eq('jhipsterSampleApplicationApp.league.home.createOrEditLabel');
    await leagueUpdatePage.cancel();
  });

  it('should create and save Leagues', async () => {
    const nbButtonsBeforeCreate = await leagueComponentsPage.countDeleteButtons();

    await leagueComponentsPage.clickOnCreateButton();

    await promise.all([
      leagueUpdatePage.setNameInput('name'),
      leagueUpdatePage.sportSelectLastOption(),
      leagueUpdatePage.regionSelectLastOption(),
    ]);

    expect(await leagueUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');

    await leagueUpdatePage.save();
    expect(await leagueUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await leagueComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last League', async () => {
    const nbButtonsBeforeDelete = await leagueComponentsPage.countDeleteButtons();
    await leagueComponentsPage.clickOnLastDeleteButton();

    leagueDeleteDialog = new LeagueDeleteDialog();
    expect(await leagueDeleteDialog.getDialogTitle()).to.eq('jhipsterSampleApplicationApp.league.delete.question');
    await leagueDeleteDialog.clickOnConfirmButton();

    expect(await leagueComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
