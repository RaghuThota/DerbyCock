package com.bean;

import javax.swing.UIManager;

import com.project.derby.action.RegistrationForm;

import javax.swing.ImageIcon;

public class SplashScreenMain {

  SplashScreen screen;

  public SplashScreenMain() {
    // initialize the splash screen
    splashScreenInit();
    // do something here to simulate the program doing something that
    // is time consuming
    for (int i = 0; i <= 1200; i++)
    {
      for (long j=0; j<50000; ++j)
      {
        String poop = " " + (j + i);
      }
      // run either of these two -- not both
      screen.setProgress("FigtClub....", i);  // progress bar with a message
      //screen.setProgress(i);           // progress bar with no message
    }
   new RegistrationForm();
  //  splashScreenDestruct();
 //  System.exit(0);
  }

  private void splashScreenDestruct() {
    screen.setScreenVisible(false);
  }

  private void splashScreenInit() {
    ImageIcon myImage = new ImageIcon(com.bean.SplashScreenMain.class.getResource("fightclub.gif"));
    screen = new SplashScreen(myImage);
    screen.setLocationRelativeTo(null);
    screen.setProgressMax(1000);
    screen.setScreenVisible(true);
  }

  public static void main(String[] args)
  {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    new SplashScreenMain();
  }

}
