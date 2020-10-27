import { element, by, ElementFinder } from 'protractor';

export class LeagueComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-league div table .btn-danger'));
  title = element.all(by.css('jhi-league div h2#page-heading span')).first();
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

export class LeagueUpdatePage {
  pageTitle = element(by.id('jhi-league-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nameInput = element(by.id('field_name'));

  sportSelect = element(by.id('field_sport'));
  regionSelect = element(by.id('field_region'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameInput(name: string): Promise<void> {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput(): Promise<string> {
    return await this.nameInput.getAttribute('value');
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

export class LeagueDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-league-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-league'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
