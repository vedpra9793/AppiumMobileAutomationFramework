package com.automate.pages;

import com.automate.enums.WaitStrategy;
import com.automate.pages.screen.ScreenActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public final class ProductPage extends ScreenActions {

  @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Home\"]")
  private static MobileElement productPageTitle;

  public String getHomePageText() {
    return getText(productPageTitle, WaitStrategy.VISIBLE);
  }
}
