import { element, by, ElementFinder } from 'protractor';

export class OpportunityComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-opportunity div table .btn-danger'));
  title = element.all(by.css('jhi-opportunity div h2#page-heading span')).first();
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

export class OpportunityUpdatePage {
  pageTitle = element(by.id('jhi-opportunity-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  timeOfMessageInput = element(by.id('field_timeOfMessage'));
  opportunityIdInput = element(by.id('field_opportunityId'));
  typeInput = element(by.id('field_type'));
  handicapInput = element(by.id('field_handicap'));
  lineInput = element(by.id('field_line'));
  sequenceInput = element(by.id('field_sequence'));
  tradingStatusInput = element(by.id('field_tradingStatus'));
  actualTradingTimeInput = element(by.id('field_actualTradingTime'));
  noteInput = element(by.id('field_note'));
  betStopInput = element(by.id('field_betStop'));
  resultsInput = element(by.id('field_results'));

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

  async setOpportunityIdInput(opportunityId: string): Promise<void> {
    await this.opportunityIdInput.sendKeys(opportunityId);
  }

  async getOpportunityIdInput(): Promise<string> {
    return await this.opportunityIdInput.getAttribute('value');
  }

  async setTypeInput(type: string): Promise<void> {
    await this.typeInput.sendKeys(type);
  }

  async getTypeInput(): Promise<string> {
    return await this.typeInput.getAttribute('value');
  }

  async setHandicapInput(handicap: string): Promise<void> {
    await this.handicapInput.sendKeys(handicap);
  }

  async getHandicapInput(): Promise<string> {
    return await this.handicapInput.getAttribute('value');
  }

  async setLineInput(line: string): Promise<void> {
    await this.lineInput.sendKeys(line);
  }

  async getLineInput(): Promise<string> {
    return await this.lineInput.getAttribute('value');
  }

  async setSequenceInput(sequence: string): Promise<void> {
    await this.sequenceInput.sendKeys(sequence);
  }

  async getSequenceInput(): Promise<string> {
    return await this.sequenceInput.getAttribute('value');
  }

  async setTradingStatusInput(tradingStatus: string): Promise<void> {
    await this.tradingStatusInput.sendKeys(tradingStatus);
  }

  async getTradingStatusInput(): Promise<string> {
    return await this.tradingStatusInput.getAttribute('value');
  }

  async setActualTradingTimeInput(actualTradingTime: string): Promise<void> {
    await this.actualTradingTimeInput.sendKeys(actualTradingTime);
  }

  async getActualTradingTimeInput(): Promise<string> {
    return await this.actualTradingTimeInput.getAttribute('value');
  }

  async setNoteInput(note: string): Promise<void> {
    await this.noteInput.sendKeys(note);
  }

  async getNoteInput(): Promise<string> {
    return await this.noteInput.getAttribute('value');
  }

  async setBetStopInput(betStop: string): Promise<void> {
    await this.betStopInput.sendKeys(betStop);
  }

  async getBetStopInput(): Promise<string> {
    return await this.betStopInput.getAttribute('value');
  }

  async setResultsInput(results: string): Promise<void> {
    await this.resultsInput.sendKeys(results);
  }

  async getResultsInput(): Promise<string> {
    return await this.resultsInput.getAttribute('value');
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

export class OpportunityDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-opportunity-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-opportunity'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
