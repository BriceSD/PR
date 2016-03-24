/*
 * Compteur.java
 * SAUNIER DEBES Brice
 * 23/03/16
 */


public class Compteur {
  public int n = 0;
  private int nfin;

  public Compteur(int fin) {
    nfin = fin;
  }

  public void comptons() {
    for (int i = 0; i < nfin; i++) {
      incrementerN();
      try {
        Thread.sleep(1);
      } catch (Exception e) {
      }
    }
    System.out.println("Total : " + Integer.toString(n));
  }

  private synchronized void incrementerN() {n++;}

  public static void main(String[] args) {
    Compteur    cpt = new Compteur(100);
    LanceCompte lc1 = new LanceCompte(cpt);
    LanceCompte lc2 = new LanceCompte(cpt);
    lc1.start();
    lc2.start();
    try {
      lc1.join();
      lc2.join();
    } catch (Exception e) {
      return;
    }
  }
}


class LanceCompte
    extends Thread {
  Compteur cpt;

  LanceCompte(Compteur cp) {
    cpt = cp;
  }

  public void run() {
    cpt.comptons();
  }
}
