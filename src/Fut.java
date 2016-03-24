/*
 * Fut.java
 * SAUNIER DEBES Brice
 * 23/03/16
 */


public class Fut {
  private int quantiteeDisponible;
  private int quantiteeMax;

  public Fut(int quantiteeDisponible, int quantiteeMax) {
    this.quantiteeDisponible = quantiteeDisponible;
    this.quantiteeMax = quantiteeMax;
  }

  public synchronized void retirer(int quantitee, Buveur buveur) {
    while (!hasAssezEnStock(quantitee)) {
      try {
        buveur.afficherMessageAttenteBoire();
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    quantiteeDisponible -= quantitee;
    buveur.quantiteeBue += quantitee;
    buveur.afficherBoireMessage();
    afficherInformationsFut();
    notifyAll();
  }

  public synchronized void ajouter(int quantitee, Barman barman) {
    while (isFull()) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    quantiteeDisponible += quantitee;
    barman.quantiteeApprovisionner += quantitee;
    barman.afficherApprovisionnementMessage();
    afficherInformationsFut();
    notifyAll();
  }

  private boolean isFull() {
    return quantiteeDisponible >= quantiteeMax;
  }

  private boolean hasAssezEnStock(int quantitee) {
    return quantiteeDisponible - quantitee >= 0;
  }

  private void afficherInformationsFut() {
    System.out.println("Il reste " + quantiteeDisponible + "L dans le fût");
  }

  public static void main(String[] args) {
    Fut fut = new Fut(4, 7);

    Barman barman1 = new Barman("Barman1", 7, fut);
    Barman barman2 = new Barman("Barman2", 4, fut);

    Buveur buveur1 = new Buveur("Buveur1", 4, fut);
    Buveur buveur2 = new Buveur("Buveur2", 1, fut);
    Buveur buveur3 = new Buveur("Buveur3", 8, fut);
    Buveur buveur4 = new Buveur("Buveur4", 2, fut);

    barman1.start();
    barman2.start();
    buveur1.start();
    buveur2.start();
    buveur3.start();
    buveur4.start();
  }
}
