/*
 * Buveur.java
 * SAUNIER DEBES Brice
 * 23/03/16
 */


public class Buveur extends Thread{
  private String nom;
  private int    quantiteeSoif;
  public  int    quantiteeBue;
  private Fut    fut;

  public Buveur(String nom, int quantiteeSoif, Fut fut) {
    this.nom = nom;
    this.quantiteeSoif = quantiteeSoif;
    this.quantiteeBue = 0;
    this.fut = fut;
  }

  public void boire() {
      fut.retirer(1, this);
  }

  public void run(){
    for (int i = 0; i < quantiteeSoif; i++) {
      try {
        Thread.sleep(getTempsAAttendre());
        boire();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("\t" + nom + " a fini de boire.");
  }

  public void afficherBoireMessage() {
    System.out.println(nom + " a bu en tout " + quantiteeBue + "L");
  }

  public void afficherMessageAttenteBoire() {
    System.out.println(nom + " est en train d’attendre pour pouvoir boire.");
  }

  private int getTempsAAttendre() {
    return (int) (Math.random() * 100);
  }
}
