package com.tests;

import base.BaseTest;
import com.automate.constants.StringConstants;
import com.automate.customannotations.FrameworkAnnotation;
import com.automate.entity.TestData;
import com.automate.enums.CategoryType;
import com.automate.pages.LoginPage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LoginTest extends BaseTest {

  LoginPage loginPage;

  @BeforeMethod
  public void initialize() {
    loginPage = new LoginPage();
  }

  @FrameworkAnnotation(author = "User-1", category = {CategoryType.REGRESSION, CategoryType.SMOKE})
  @Test(description = "Incorrect Username and correct password combination",priority = 0)
  public void invalidLoginIncorrectEmail(TestData data) {
    loginPage.skipInfoPage().setUsername("shubhada@test")
      .setPassword("Ved@9793")
      .tapOnLogin();
    Assert.assertEquals("invalidLoginErrorMessage", StringConstants.INVALID_LOGIN_ERROR_MESSAGE, "Assertion for Invalid login error");
  }

  @FrameworkAnnotation(author = "User-2", category = {CategoryType.REGRESSION, CategoryType.SANITY})
  @Test(description = "Correct email and incorrect password",priority = 1)
  public void invalidLoginIncorrectPass(TestData data) {
    loginPage.setUsername("vedpra9793@gmail.com")
      .setPassword("shubhada@test")
      .tapOnLogin();
    String invalidLoginErrorMessage = loginPage.getPasswordErrorText();
    Assert.assertEquals(invalidLoginErrorMessage, StringConstants.HOME_PAGE_TITLE, "Home");
  }

  @FrameworkAnnotation(author = "User-3", category = {CategoryType.REGRESSION, CategoryType.SANITY})
  @Test(description = "Correct Username and Password combination",priority = 2)
  public void validLogin(TestData data) {
    String HomePageTitle = loginPage.setUsername("vedpra9793@gmail.com")
      .setPassword("Ved@9793")
      .tapOnLogin()
    .getHomePageText();
   Assert.assertEquals(HomePageTitle, StringConstants.HOME_PAGE_TITLE, "Home");
  }
}
