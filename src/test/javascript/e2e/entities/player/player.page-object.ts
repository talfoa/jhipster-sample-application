import { element, by, ElementFinder } from 'protractor';

export class PlayerComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-player div table .btn-danger'));
  title = element.all(by.css('jhi-player div h2#page-heading span')).first();
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

export class PlayerUpdatePage {
  pageTitle = element(by.id('jhi-player-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nameInput = element(by.id('field_name'));
  nickNameInput = element(by.id('field_nickName'));
  shirtNumberInput = element(by.id('field_shirtNumber'));
  positionInput = element(by.id('field_position'));

  teamSelect = element(by.id('field_team'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameInput(name: string): Promise<void> {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput(): Promise<string> {
    return await this.nameInput.getAttribute('value');
  }

  async setNickNameInput(nickName: string): Promise<void> {
    await this.nickNameInput.sendKeys(nickName);
  }

  async getNickNameInput(): Promise<string> {
    return await this.nickNameInput.getAttribute('value');
  }

  async setShirtNumberInput(shirtNumber: string): Promise<void> {
    await this.shirtNumberInput.sendKeys(shirtNumber);
  }

  async getShirtNumberInput(): Promise<string> {
    return await this.shirtNumberInput.getAttribute('value');
  }

  async setPositionInput(position: string): Promise<void> {
    await this.positionInput.sendKeys(position);
  }

  async getPositionInput(): Promise<string> {
    return await this.positionInput.getAttribute('value');
  }

  async teamSelectLastOption(): Promise<void> {
    await this.teamSelect.all(by.tagName('option')).last().click();
  }

  async teamSelectOption(option: string): Promise<void> {
    await this.teamSelect.sendKeys(option);
  }

  getTeamSelect(): ElementFinder {
    return this.teamSelect;
  }

  async getTeamSelectedOption(): Promise<string> {
    return await this.teamSelect.element(by.css('option:checked')).getText();
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

export class PlayerDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-player-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-player'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
