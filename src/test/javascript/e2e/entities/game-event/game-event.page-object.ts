import { element, by, ElementFinder } from 'protractor';

export class GameEventComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-game-event div table .btn-danger'));
  title = element.all(by.css('jhi-game-event div h2#page-heading span')).first();
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

export class GameEventUpdatePage {
  pageTitle = element(by.id('jhi-game-event-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  timeOfMessageInput = element(by.id('field_timeOfMessage'));
  gameEventIdInput = element(by.id('field_gameEventId'));
  eventTypeSelect = element(by.id('field_eventType'));
  teamSelect = element(by.id('field_team'));
  activeInput = element(by.id('field_active'));
  timeOfEventOccurenceInput = element(by.id('field_timeOfEventOccurence'));

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

  async setGameEventIdInput(gameEventId: string): Promise<void> {
    await this.gameEventIdInput.sendKeys(gameEventId);
  }

  async getGameEventIdInput(): Promise<string> {
    return await this.gameEventIdInput.getAttribute('value');
  }

  async setEventTypeSelect(eventType: string): Promise<void> {
    await this.eventTypeSelect.sendKeys(eventType);
  }

  async getEventTypeSelect(): Promise<string> {
    return await this.eventTypeSelect.element(by.css('option:checked')).getText();
  }

  async eventTypeSelectLastOption(): Promise<void> {
    await this.eventTypeSelect.all(by.tagName('option')).last().click();
  }

  async setTeamSelect(team: string): Promise<void> {
    await this.teamSelect.sendKeys(team);
  }

  async getTeamSelect(): Promise<string> {
    return await this.teamSelect.element(by.css('option:checked')).getText();
  }

  async teamSelectLastOption(): Promise<void> {
    await this.teamSelect.all(by.tagName('option')).last().click();
  }

  getActiveInput(): ElementFinder {
    return this.activeInput;
  }

  async setTimeOfEventOccurenceInput(timeOfEventOccurence: string): Promise<void> {
    await this.timeOfEventOccurenceInput.sendKeys(timeOfEventOccurence);
  }

  async getTimeOfEventOccurenceInput(): Promise<string> {
    return await this.timeOfEventOccurenceInput.getAttribute('value');
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

export class GameEventDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-gameEvent-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-gameEvent'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
