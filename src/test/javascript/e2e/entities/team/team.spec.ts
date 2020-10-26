import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TeamComponentsPage, TeamDeleteDialog, TeamUpdatePage } from './team.page-object';

const expect = chai.expect;

describe('Team e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let teamComponentsPage: TeamComponentsPage;
  let teamUpdatePage: TeamUpdatePage;
  let teamDeleteDialog: TeamDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Teams', async () => {
    await navBarPage.goToEntity('team');
    teamComponentsPage = new TeamComponentsPage();
    await browser.wait(ec.visibilityOf(teamComponentsPage.title), 5000);
    expect(await teamComponentsPage.getTitle()).to.eq('jhipsterSampleApplicationApp.team.home.title');
    await browser.wait(ec.or(ec.visibilityOf(teamComponentsPage.entities), ec.visibilityOf(teamComponentsPage.noResult)), 1000);
  });

  it('should load create Team page', async () => {
    await teamComponentsPage.clickOnCreateButton();
    teamUpdatePage = new TeamUpdatePage();
    expect(await teamUpdatePage.getPageTitle()).to.eq('jhipsterSampleApplicationApp.team.home.createOrEditLabel');
    await teamUpdatePage.cancel();
  });

  it('should create and save Teams', async () => {
    const nbButtonsBeforeCreate = await teamComponentsPage.countDeleteButtons();

    await teamComponentsPage.clickOnCreateButton();

    await promise.all([
      teamUpdatePage.setNameInput('name'),
      teamUpdatePage.setColorInput('color'),
      teamUpdatePage.setFlagInput('flag'),
      teamUpdatePage.sportSelectLastOption(),
      // teamUpdatePage.leagueSelectLastOption(),
    ]);

    expect(await teamUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await teamUpdatePage.getColorInput()).to.eq('color', 'Expected Color value to be equals to color');
    expect(await teamUpdatePage.getFlagInput()).to.eq('flag', 'Expected Flag value to be equals to flag');

    await teamUpdatePage.save();
    expect(await teamUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await teamComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Team', async () => {
    const nbButtonsBeforeDelete = await teamComponentsPage.countDeleteButtons();
    await teamComponentsPage.clickOnLastDeleteButton();

    teamDeleteDialog = new TeamDeleteDialog();
    expect(await teamDeleteDialog.getDialogTitle()).to.eq('jhipsterSampleApplicationApp.team.delete.question');
    await teamDeleteDialog.clickOnConfirmButton();

    expect(await teamComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
