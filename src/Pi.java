/*
 * MonteCarlo.java
 * SAUNIER DEBES Brice
 * 23/03/16
 */


public class Pi extends Thread{
  private Point[] points;
  private int chunkSize;

  public Pi(int nbPoints) {
    chunkSize = nbPoints/4;
    points = new Point[nbPoints];
    for (int i = 0; i < nbPoints; i++)
      points[i] = new Point(Math.random(), Math.random());

  }

  public double getPI() {
    return 4.0 * getNombrePointsDansCercle() / points.length;
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

  public static void main(String[] args) {
    Pi pi = new Pi(1000000);
    System.out.println(pi.getPI());
  }
}
