import { element, by, ElementFinder } from 'protractor';

export class TeamComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-team div table .btn-danger'));
  title = element.all(by.css('jhi-team div h2#page-heading span')).first();
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

export class TeamUpdatePage {
  pageTitle = element(by.id('jhi-team-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nameInput = element(by.id('field_name'));
  colorInput = element(by.id('field_color'));
  flagInput = element(by.id('field_flag'));

  leagueSelect = element(by.id('field_league'));
  sportSelect = element(by.id('field_sport'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameInput(name: string): Promise<void> {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput(): Promise<string> {
    return await this.nameInput.getAttribute('value');
  }

  async setColorInput(color: string): Promise<void> {
    await this.colorInput.sendKeys(color);
  }

  async getColorInput(): Promise<string> {
    return await this.colorInput.getAttribute('value');
  }

  async setFlagInput(flag: string): Promise<void> {
    await this.flagInput.sendKeys(flag);
  }

  async getFlagInput(): Promise<string> {
    return await this.flagInput.getAttribute('value');
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

export class TeamDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-team-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-team'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
