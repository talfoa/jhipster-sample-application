import { element, by, ElementFinder } from 'protractor';

export class MatchComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-match div table .btn-danger'));
  title = element.all(by.css('jhi-match div h2#page-heading span')).first();
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

export class MatchUpdatePage {
  pageTitle = element(by.id('jhi-match-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  timeOfMessageInput = element(by.id('field_timeOfMessage'));
  matchIdInput = element(by.id('field_matchId'));
  matchDateInput = element(by.id('field_matchDate'));
  cornerSendingInput = element(by.id('field_cornerSending'));

  homeTeamSelect = element(by.id('field_homeTeam'));
  awayTeamSelect = element(by.id('field_awayTeam'));
  leagueSelect = element(by.id('field_league'));
  sportSelect = element(by.id('field_sport'));
  regionSelect = element(by.id('field_region'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTimeOfMessageInput(timeOfMessage: string): Promise<void> {
    await this.timeOfMessageInput.sendKeys(timeOfMessage);
  }

  async getTimeOfMessageInput(): Promise<string> {
    return await this.timeOfMessageInput.getAttribute('value');
  }

  async setMatchIdInput(matchId: string): Promise<void> {
    await this.matchIdInput.sendKeys(matchId);
  }

  async getMatchIdInput(): Promise<string> {
    return await this.matchIdInput.getAttribute('value');
  }

  async setMatchDateInput(matchDate: string): Promise<void> {
    await this.matchDateInput.sendKeys(matchDate);
  }

  async getMatchDateInput(): Promise<string> {
    return await this.matchDateInput.getAttribute('value');
  }

  getCornerSendingInput(): ElementFinder {
    return this.cornerSendingInput;
  }

  async homeTeamSelectLastOption(): Promise<void> {
    await this.homeTeamSelect.all(by.tagName('option')).last().click();
  }

  async homeTeamSelectOption(option: string): Promise<void> {
    await this.homeTeamSelect.sendKeys(option);
  }

  getHomeTeamSelect(): ElementFinder {
    return this.homeTeamSelect;
  }

  async getHomeTeamSelectedOption(): Promise<string> {
    return await this.homeTeamSelect.element(by.css('option:checked')).getText();
  }

  async awayTeamSelectLastOption(): Promise<void> {
    await this.awayTeamSelect.all(by.tagName('option')).last().click();
  }

  async awayTeamSelectOption(option: string): Promise<void> {
    await this.awayTeamSelect.sendKeys(option);
  }

  getAwayTeamSelect(): ElementFinder {
    return this.awayTeamSelect;
  }

  async getAwayTeamSelectedOption(): Promise<string> {
    return await this.awayTeamSelect.element(by.css('option:checked')).getText();
  }

  async leagueSelectLastOption(): Promise<void> {
    await this.leagueSelect.all(by.tagName('option')).last().click();
  }

  async leagueSelectOption(option: string): Promise<void> {
    await this.leagueSelect.sendKeys(option);
  }

  getLeagueSelect(): ElementFinder {
    return this.leagueSelect;
  }

  async getLeagueSelectedOption(): Promise<string> {
    return await this.leagueSelect.element(by.css('option:checked')).getText();
  }

  async sportSelectLastOption(): Promise<void> {
    await this.sportSelect.all(by.tagName('option')).last().click();
  }

  async sportSelectOption(option: string): Promise<void> {
    await this.sportSelect.sendKeys(option);
  }

  getSportSelect(): ElementFinder {
    return this.sportSelect;
  }

  async getSportSelectedOption(): Promise<string> {
    return await this.sportSelect.element(by.css('option:checked')).getText();
  }

  async regionSelectLastOption(): Promise<void> {
    await this.regionSelect.all(by.tagName('option')).last().click();
  }

  async regionSelectOption(option: string): Promise<void> {
    await this.regionSelect.sendKeys(option);
  }

  getRegionSelect(): ElementFinder {
    return this.regionSelect;
  }

  async getRegionSelectedOption(): Promise<string> {
    return await this.regionSelect.element(by.css('option:checked')).getText();
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

export class MatchDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-match-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-match'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
