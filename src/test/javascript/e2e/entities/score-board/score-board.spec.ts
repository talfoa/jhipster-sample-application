import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ScoreBoardComponentsPage, ScoreBoardDeleteDialog, ScoreBoardUpdatePage } from './score-board.page-object';

const expect = chai.expect;

describe('ScoreBoard e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let scoreBoardComponentsPage: ScoreBoardComponentsPage;
  let scoreBoardUpdatePage: ScoreBoardUpdatePage;
  let scoreBoardDeleteDialog: ScoreBoardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ScoreBoards', async () => {
    await navBarPage.goToEntity('score-board');
    scoreBoardComponentsPage = new ScoreBoardComponentsPage();
    await browser.wait(ec.visibilityOf(scoreBoardComponentsPage.title), 5000);
    expect(await scoreBoardComponentsPage.getTitle()).to.eq('jhipsterSampleApplicationApp.scoreBoard.home.title');
    await browser.wait(ec.or(ec.visibilityOf(scoreBoardComponentsPage.entities), ec.visibilityOf(scoreBoardComponentsPage.noResult)), 1000);
  });

  it('should load create ScoreBoard page', async () => {
    await scoreBoardComponentsPage.clickOnCreateButton();
    scoreBoardUpdatePage = new ScoreBoardUpdatePage();
    expect(await scoreBoardUpdatePage.getPageTitle()).to.eq('jhipsterSampleApplicationApp.scoreBoard.home.createOrEditLabel');
    await scoreBoardUpdatePage.cancel();
  });

  it('should create and save ScoreBoards', async () => {
    const nbButtonsBeforeCreate = await scoreBoardComponentsPage.countDeleteButtons();

    await scoreBoardComponentsPage.clickOnCreateButton();

    await promise.all([
      scoreBoardUpdatePage.setTimeOfMessageInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      scoreBoardUpdatePage.setGamePartInput('gamePart'),
      scoreBoardUpdatePage.setScoreInput('score'),
      scoreBoardUpdatePage.setScorePartInput('scorePart'),
      scoreBoardUpdatePage.setRemainingTimeInPeriodInput('5'),
      scoreBoardUpdatePage.setRelativePlayerCountInput('5'),
      scoreBoardUpdatePage.matchSelectLastOption(),
    ]);

    expect(await scoreBoardUpdatePage.getTimeOfMessageInput()).to.contain(
      '2001-01-01T02:30',
      'Expected timeOfMessage value to be equals to 2000-12-31'
    );
    expect(await scoreBoardUpdatePage.getGamePartInput()).to.eq('gamePart', 'Expected GamePart value to be equals to gamePart');
    expect(await scoreBoardUpdatePage.getScoreInput()).to.eq('score', 'Expected Score value to be equals to score');
    expect(await scoreBoardUpdatePage.getScorePartInput()).to.eq('scorePart', 'Expected ScorePart value to be equals to scorePart');
    const selectedHidden = scoreBoardUpdatePage.getHiddenInput();
    if (await selectedHidden.isSelected()) {
      await scoreBoardUpdatePage.getHiddenInput().click();
      expect(await scoreBoardUpdatePage.getHiddenInput().isSelected(), 'Expected hidden not to be selected').to.be.false;
    } else {
      await scoreBoardUpdatePage.getHiddenInput().click();
      expect(await scoreBoardUpdatePage.getHiddenInput().isSelected(), 'Expected hidden to be selected').to.be.true;
    }
    const selectedHideTimer = scoreBoardUpdatePage.getHideTimerInput();
    if (await selectedHideTimer.isSelected()) {
      await scoreBoardUpdatePage.getHideTimerInput().click();
      expect(await scoreBoardUpdatePage.getHideTimerInput().isSelected(), 'Expected hideTimer not to be selected').to.be.false;
    } else {
      await scoreBoardUpdatePage.getHideTimerInput().click();
      expect(await scoreBoardUpdatePage.getHideTimerInput().isSelected(), 'Expected hideTimer to be selected').to.be.true;
    }
    expect(await scoreBoardUpdatePage.getRemainingTimeInPeriodInput()).to.eq('5', 'Expected remainingTimeInPeriod value to be equals to 5');
    expect(await scoreBoardUpdatePage.getRelativePlayerCountInput()).to.eq('5', 'Expected relativePlayerCount value to be equals to 5');

    await scoreBoardUpdatePage.save();
    expect(await scoreBoardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await scoreBoardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ScoreBoard', async () => {
    const nbButtonsBeforeDelete = await scoreBoardComponentsPage.countDeleteButtons();
    await scoreBoardComponentsPage.clickOnLastDeleteButton();

    scoreBoardDeleteDialog = new ScoreBoardDeleteDialog();
    expect(await scoreBoardDeleteDialog.getDialogTitle()).to.eq('jhipsterSampleApplicationApp.scoreBoard.delete.question');
    await scoreBoardDeleteDialog.clickOnConfirmButton();

    expect(await scoreBoardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
