import { element, by, ElementFinder } from 'protractor';

export class ScoreBoardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-score-board div table .btn-danger'));
  title = element.all(by.css('jhi-score-board div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class ScoreBoardUpdatePage {
  pageTitle = element(by.id('jhi-score-board-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  timeOfMessageInput = element(by.id('field_timeOfMessage'));
  gamePartInput = element(by.id('field_gamePart'));
  scoreInput = element(by.id('field_score'));
  scorePartInput = element(by.id('field_scorePart'));
  hiddenInput = element(by.id('field_hidden'));
  hideTimerInput = element(by.id('field_hideTimer'));
  remainingTimeInPeriodInput = element(by.id('field_remainingTimeInPeriod'));
  relativePlayerCountInput = element(by.id('field_relativePlayerCount'));

  matchSelect = element(by.id('field_match'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTimeOfMessageInput(timeOfMessage: string): Promise<void> {
    await this.timeOfMessageInput.sendKeys(timeOfMessage);
  }

  async getTimeOfMessageInput(): Promise<string> {
    return await this.timeOfMessageInput.getAttribute('value');
  }

  async setGamePartInput(gamePart: string): Promise<void> {
    await this.gamePartInput.sendKeys(gamePart);
  }

  async getGamePartInput(): Promise<string> {
    return await this.gamePartInput.getAttribute('value');
  }

  async setScoreInput(score: string): Promise<void> {
    await this.scoreInput.sendKeys(score);
  }

  async getScoreInput(): Promise<string> {
    return await this.scoreInput.getAttribute('value');
  }

  async setScorePartInput(scorePart: string): Promise<void> {
    await this.scorePartInput.sendKeys(scorePart);
  }

  async getScorePartInput(): Promise<string> {
    return await this.scorePartInput.getAttribute('value');
  }

  getHiddenInput(): ElementFinder {
    return this.hiddenInput;
  }

  getHideTimerInput(): ElementFinder {
    return this.hideTimerInput;
  }

  async setRemainingTimeInPeriodInput(remainingTimeInPeriod: string): Promise<void> {
    await this.remainingTimeInPeriodInput.sendKeys(remainingTimeInPeriod);
  }

  async getRemainingTimeInPeriodInput(): Promise<string> {
    return await this.remainingTimeInPeriodInput.getAttribute('value');
  }

  async setRelativePlayerCountInput(relativePlayerCount: string): Promise<void> {
    await this.relativePlayerCountInput.sendKeys(relativePlayerCount);
  }

  async getRelativePlayerCountInput(): Promise<string> {
    return await this.relativePlayerCountInput.getAttribute('value');
  }

  async matchSelectLastOption(): Promise<void> {
    await this.matchSelect.all(by.tagName('option')).last().click();
  }

  async matchSelectOption(option: string): Promise<void> {
    await this.matchSelect.sendKeys(option);
  }

  getMatchSelect(): ElementFinder {
    return this.matchSelect;
  }

  async getMatchSelectedOption(): Promise<string> {
    return await this.matchSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class ScoreBoardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-scoreBoard-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-scoreBoard'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
