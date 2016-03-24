/*
 * Pithread.java
 * SAUNIER DEBES Brice
 * 23/03/16
 */


public class Pithread extends Thread{
  public Point[] points;

  public Pithread(int nbPoints) {
    points = new Point[nbPoints];
    for (int i = 0; i < nbPoints; i++)
      points[i] = new Point(Math.random(), Math.random());

  }

  public double getPI() {
    return 4.0 * getNombrePointsDansCercle() / points.length;
  }

  public double getPI(int nombrePtDansCercle) {
    return 4.0 * nombrePtDansCercle / points.length;
  }

  private int getNombrePointsDansCercle() {
    int nb = 0;
    for (int i = 0; i < points.length; i++) {
      if (Math.pow(points[i].x, 2) + Math.pow(points[i].y, 2) < 1)
        nb++;
    }
    return nb;
  }
  //pi = 4* nombrePointDansCercle/nombrePoints

}
