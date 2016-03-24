/*
 * Classement.java
 * SAUNIER DEBES Brice
 * 23/03/16
 */


public class Classement {
  private int classement;

  public Classement() {
    this.classement = 1;
  }

  public synchronized int getClassement(){
    return classement++;
  }
  public void resetClassement(){
    classement = 1;
  }

}

