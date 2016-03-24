/*
 * Barman.java
 * SAUNIER DEBES Brice
 * 23/03/16
 */


public class Barman extends Thread{
  private String nom;
  private int    quantiteeBoisson;
  public int    quantiteeApprovisionner;
  private Fut    fut;

  public Barman(String nom, int quantiteeBoisson, Fut fut){
    this.fut = fut;
    this.nom = nom;
    this.quantiteeBoisson = quantiteeBoisson;
  }

  public void approvisionner(){
    fut.ajouter(2, this);
  }

  public void run(){
    for (int i = 0; (i*2) < quantiteeBoisson; i++) {
      try {
        Thread.sleep(getTempsAAttendre());
        approvisionner();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void afficherApprovisionnementMessage() {
    System.out.println(nom + " a approvisionné en tout " + quantiteeApprovisionner + "L");
  }

  private int getTempsAAttendre() {
    return (int) (Math.random() * 1000);
  }
}
