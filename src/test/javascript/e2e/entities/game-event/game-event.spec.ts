import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GameEventComponentsPage, GameEventDeleteDialog, GameEventUpdatePage } from './game-event.page-object';

const expect = chai.expect;

describe('GameEvent e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let gameEventComponentsPage: GameEventComponentsPage;
  let gameEventUpdatePage: GameEventUpdatePage;
  let gameEventDeleteDialog: GameEventDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GameEvents', async () => {
    await navBarPage.goToEntity('game-event');
    gameEventComponentsPage = new GameEventComponentsPage();
    await browser.wait(ec.visibilityOf(gameEventComponentsPage.title), 5000);
    expect(await gameEventComponentsPage.getTitle()).to.eq('jhipsterSampleApplicationApp.gameEvent.home.title');
    await browser.wait(ec.or(ec.visibilityOf(gameEventComponentsPage.entities), ec.visibilityOf(gameEventComponentsPage.noResult)), 1000);
  });

  it('should load create GameEvent page', async () => {
    await gameEventComponentsPage.clickOnCreateButton();
    gameEventUpdatePage = new GameEventUpdatePage();
    expect(await gameEventUpdatePage.getPageTitle()).to.eq('jhipsterSampleApplicationApp.gameEvent.home.createOrEditLabel');
    await gameEventUpdatePage.cancel();
  });

  it('should create and save GameEvents', async () => {
    const nbButtonsBeforeCreate = await gameEventComponentsPage.countDeleteButtons();

    await gameEventComponentsPage.clickOnCreateButton();

    await promise.all([
      gameEventUpdatePage.setTimeOfMessageInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      gameEventUpdatePage.setGameEventIdInput('5'),
      gameEventUpdatePage.eventTypeSelectLastOption(),
      gameEventUpdatePage.teamSelectLastOption(),
      gameEventUpdatePage.setTimeOfEventOccurenceInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      gameEventUpdatePage.matchSelectLastOption(),
    ]);

    expect(await gameEventUpdatePage.getTimeOfMessageInput()).to.contain(
      '2001-01-01T02:30',
      'Expected timeOfMessage value to be equals to 2000-12-31'
    );
    expect(await gameEventUpdatePage.getGameEventIdInput()).to.eq('5', 'Expected gameEventId value to be equals to 5');
    const selectedActive = gameEventUpdatePage.getActiveInput();
    if (await selectedActive.isSelected()) {
      await gameEventUpdatePage.getActiveInput().click();
      expect(await gameEventUpdatePage.getActiveInput().isSelected(), 'Expected active not to be selected').to.be.false;
    } else {
      await gameEventUpdatePage.getActiveInput().click();
      expect(await gameEventUpdatePage.getActiveInput().isSelected(), 'Expected active to be selected').to.be.true;
    }
    expect(await gameEventUpdatePage.getTimeOfEventOccurenceInput()).to.contain(
      '2001-01-01T02:30',
      'Expected timeOfEventOccurence value to be equals to 2000-12-31'
    );

    await gameEventUpdatePage.save();
    expect(await gameEventUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await gameEventComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GameEvent', async () => {
    const nbButtonsBeforeDelete = await gameEventComponentsPage.countDeleteButtons();
    await gameEventComponentsPage.clickOnLastDeleteButton();

    gameEventDeleteDialog = new GameEventDeleteDialog();
    expect(await gameEventDeleteDialog.getDialogTitle()).to.eq('jhipsterSampleApplicationApp.gameEvent.delete.question');
    await gameEventDeleteDialog.clickOnConfirmButton();

    expect(await gameEventComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
