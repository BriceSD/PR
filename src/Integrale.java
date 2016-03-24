/*
 * Integrale.java
 * SAUNIER DEBES Brice
 * 23/03/16
 */


public class Integrale {
  public  Point[] points;
  private int     nbPointSousIntegrale;

  public Integrale(int nbPoints) {
    points = new Point[nbPoints];
    nbPointSousIntegrale = 0;
    for (int i = 0; i < nbPoints; i++)
      points[i] = new Point(Math.random(), Math.random());
  }

  public void compter(int indiceDebut, int indiceFin) {
    for (int i = indiceDebut; i < indiceFin; i++) {
      if (f(points[i].x) > points[i].y)
        incrementerNbPointSousIntegrale();
    }
  }

  private double f(double x) {
    return 0.25 * (Math.sqrt(x) + 3 * Math.pow(x, 2));
  }

  public double getResultat() {
    return 1.0 * nbPointSousIntegrale / points.length;
  }

  private synchronized void incrementerNbPointSousIntegrale() {
    nbPointSousIntegrale++;
  }

  public static void main(String[] args) {
    Integrale      integrale       = new Integrale(1000000);
    int            chunkSize       = integrale.points.length / 4;
    LanceIntegrale lanceIntegrale1 = new LanceIntegrale(integrale, 0, chunkSize);
    LanceIntegrale lanceIntegrale2 = new LanceIntegrale(integrale, chunkSize, chunkSize * 2);
    LanceIntegrale lanceIntegrale3 = new LanceIntegrale(integrale, chunkSize * 2, chunkSize * 3);
    LanceIntegrale lanceIntegrale4 = new LanceIntegrale(integrale, chunkSize * 3, integrale.points.length);

    lanceIntegrale1.start();
    lanceIntegrale2.start();
    lanceIntegrale3.start();
    lanceIntegrale4.start();

    try {
      lanceIntegrale1.join();
      lanceIntegrale2.join();
      lanceIntegrale3.join();
      lanceIntegrale4.join();
      System.out.println(integrale.getResultat());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}


class LanceIntegrale
    extends Thread {
  Integrale integrale;
  int       indiceDebut;
  int       indiceFin;

  LanceIntegrale(Integrale integrale, int indiceDebut, int indiceFin) {
    this.integrale = integrale;
    this.indiceDebut = indiceDebut;
    this.indiceFin = indiceFin;
  }

  public void run() {
    integrale.compter(indiceDebut, indiceFin);
  }
}
