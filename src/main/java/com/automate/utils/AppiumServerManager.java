package com.automate.utils;

import com.automate.constants.FrameworkConstants;
import com.automate.enums.ConfigProperties;
import com.automate.utils.configloader.PropertyUtils;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppiumServerManager {

  private static AppiumDriverLocalService service;

  static boolean checkIfServerIsRunning(int port) {
    boolean isServerRunning = false;
    try {
      ServerSocket serverSocket = new ServerSocket(port);
      serverSocket.close();
    } catch (IOException e) {
      isServerRunning = true;
    }
    return isServerRunning;
  }

  public static void startAppiumServer() {
    if (PropertyUtils.getPropertyValue(ConfigProperties.START_APPIUM_SERVER).equalsIgnoreCase("yes")) {
      if (!AppiumServerManager.checkIfServerIsRunning(FrameworkConstants.APPIUM_SERVER_PORT)) {
        //Build the Appium service
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.usingDriverExecutable(new File(FrameworkConstants.NODEJS_PATH))
          .withAppiumJS(new File(FrameworkConstants.APPIUM_JS_PATH))
          .withIPAddress(FrameworkConstants.APPIUM_SERVER_HOST)
          .usingPort(FrameworkConstants.APPIUM_SERVER_PORT)
          .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
          .withArgument(GeneralServerFlag.ALLOW_INSECURE, "chromedriver_autodownload")
          .withLogFile(new File(FrameworkConstants.getAppiumServerLogsPath()));
        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
//				service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        service.clearOutPutStreams();
      }
    }
  }

//  public static void main(String[] args) {
//    // Set NODE_HOME environment variable if not already set
//    String nodeHome = System.getenv("NVM_INC");
////     System.getenv().forEach((k,v)->
////       System.out.println(k+" = "+v)
////       );
//    System.out.println("NODE_HOME: " + nodeHome);
//
//    // Use NODE_HOME to construct NODEJS_PATH
//
//  }

  public static void stopAppiumServer() {
    if (PropertyUtils.getPropertyValue(ConfigProperties.START_APPIUM_SERVER).equalsIgnoreCase("yes")) {
      service.stop();
      Runtime runtime = Runtime.getRuntime();
      try {
        runtime.exec("taskkill /F /IM node.exe");
        runtime.exec("taskkill /F /IM cmd.exe");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
