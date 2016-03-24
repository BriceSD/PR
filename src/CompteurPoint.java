/*
 * CompteurPoint.java
 * SAUNIER DEBES Brice
 * 23/03/16
 */


public class CompteurPoint {
  private Point[] points;
  public  int     nbPointDansCercle;

  public CompteurPoint(Point[] points) {
    this.points = points;
    this.nbPointDansCercle = 0;
  }

  public void compter(int indiceDebut, int indiceFin) {
    for (int i = indiceDebut; i < indiceFin; i++) {
      if (Math.pow(points[i].x, 2) + Math.pow(points[i].y, 2) < 1)
        incrementerNbPointDansCercle();
    }
  }

  private synchronized void incrementerNbPointDansCercle() {
    nbPointDansCercle++;
  }

  public static void main(String[] args) {
    Pithread         pithread          = new Pithread(15000000);
    CompteurPoint    compteurPoint     = new CompteurPoint(pithread.points);
    int              chunkSize         = pithread.points.length / 4;
    LanceComptePoint lanceComptePoint1 = new LanceComptePoint(compteurPoint, 0, chunkSize);
    LanceComptePoint lanceComptePoint2 =
        new LanceComptePoint(compteurPoint, chunkSize, chunkSize * 2);
    LanceComptePoint lanceComptePoint3 =
        new LanceComptePoint(compteurPoint, chunkSize * 2, chunkSize * 3);
    LanceComptePoint lanceComptePoint4 =
        new LanceComptePoint(compteurPoint, chunkSize * 3, pithread.points.length);

    lanceComptePoint1.start();
    lanceComptePoint2.start();
    lanceComptePoint3.start();
    lanceComptePoint4.start();

    try {
      lanceComptePoint1.join();
      lanceComptePoint2.join();
      lanceComptePoint3.join();
      lanceComptePoint4.join();
      System.out.println(pithread.getPI(compteurPoint.nbPointDansCercle));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


}


class LanceComptePoint
    extends Thread {
  CompteurPoint cpp;
  int           indiceDebut;
  int           indiceFin;

  LanceComptePoint(CompteurPoint cpp, int indiceDebut, int indiceFin) {
    this.cpp = cpp;
    this.indiceDebut = indiceDebut;
    this.indiceFin = indiceFin;
  }

  public void run() {
    cpp.compter(indiceDebut, indiceFin);
  }
}

