package com.automate.pages;

import com.automate.entity.LoginData;
import com.automate.enums.WaitStrategy;
import com.automate.pages.screen.ScreenActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public final class LoginPage extends ScreenActions {

  @AndroidFindBy(xpath = "//c2.k1/android.view.View/android.view.View/android.widget.EditText[1]")
  @iOSXCUITFindBy(xpath = "//c2.k1/android.view.View/android.view.View/android.widget.EditText[1]")
  private static MobileElement txtFieldUsername;

  @AndroidFindBy(xpath = "//c2.k1/android.view.View/android.view.View/android.widget.EditText[2]")
  @iOSXCUITFindBy(xpath = "//c2.k1/android.view.View/android.view.View/android.widget.EditText[2]")
  private static MobileElement txtFieldPassword;

  @AndroidFindBy(xpath = "//android.widget.Button")
  @iOSXCUITFindBy(xpath = "//android.widget.Button")
  private static MobileElement btnLogin;

  @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Error message']/android.widget.TextView")
  @iOSXCUITFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Error message']/android.widget.TextView")
  private static MobileElement errorMessage;

  @AndroidFindBy(xpath = "//android.widget.Button")
  private static MobileElement skipBT;

  @AndroidFindBy(xpath = "//android.widget.Toast[@text='Please enter valid email address.']")
  private static MobileElement incorrectEmailMsg;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.win.winfertility:id/vw_text']")
  private static MobileElement incorrectPasswordMsg;

  @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.win.winfertility:id/btn_positive']")
  private static MobileElement incorrectPasswordOK;

  public boolean isLoginPageDisplayed() {
    return isElementDisplayed(txtFieldUsername);
  }

  public LoginPage skipInfoPage(){
    click(skipBT,"skip button");
    return this;
  }
  public LoginPage setUsername(String username) {
    enter(txtFieldUsername, username, "Username");
    return this;
  }

  public LoginPage setPassword(String password) {
    enter(txtFieldPassword, password, "Password");
    return this;
  }

  public ProductPage tapOnLogin() {
    click(btnLogin, "Login");
    return new ProductPage();
  }

  public ProductPage login(LoginData loginData) {
    setUsername(loginData.getLoginUsername())
      .setPassword(loginData.getLoginPassword())
      .tapOnLogin();

    return new ProductPage();
  }

  public String getEmailErrorText() {
    return getText(incorrectEmailMsg, WaitStrategy.VISIBLE);
  }

  public String getPasswordErrorText() {
    String error = getText(incorrectPasswordMsg, WaitStrategy.VISIBLE);
    click(incorrectPasswordOK,"OK");
    return "";//getText(incorrectPasswordMsg, WaitStrategy.VISIBLE);
  }
}
