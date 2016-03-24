/*
 * Coureur.java
 * SAUNIER DEBES Brice
 * 23/03/16
 */


import java.util.ArrayList;
import java.util.List;

public class Coureur extends Thread{
  private String     nom;
  private int        distanceParcourue;
  private int        tempsCouru;
  private Classement classement;
  private int        resultatsCourse;
  private int        nbrPremierePlace;

  public Coureur(String nom, Classement classement,int nbrPremierePlace){
    this.nom = nom;
    this.distanceParcourue = 0;
    this.tempsCouru = 0;
    this.classement = classement;
    this.resultatsCourse = 0;
    this.nbrPremierePlace = nbrPremierePlace;
  }

  public void run(){
    int tempsAAttendre;

    for (int i = 0; i < 100; i++) {
      distanceParcourue ++;

      if (distanceParcourue % 10 == 0 && distanceParcourue < 100) {
        tempsAAttendre = getTempsPause();
        tempsCouru += tempsAAttendre;
        try {
          System.out.println(getMessage());
          Thread.sleep(tempsAAttendre);
        } catch (Exception e) {
        }
      }
    }
    resultatsCourse = classement.getClassement();
    if (resultatsCourse == 1)
      nbrPremierePlace += 1;
    System.out.println(getMessage());
  }

  public String getMessage(){
    return distanceParcourue == 100 ? nom + " a franchi la ligne d’arrivée à la place " + resultatsCourse + ", avec un temps de " + tempsCouru +"ms" : nom + " à parcouru " + distanceParcourue +"m";
  }

  private int getTempsPause() {
    return (int) (Math.random() * 100);
  }

  private String getStatistiques(){
    return nom + " arrive " + nbrPremierePlace*10 + "% du temps premier.";
  }

  /*
  public class Classement(){
    private int classement;

  }
  */

  public static void main(String[] args) {
    Classement classement = new Classement();
    //Classement classement;
    Coureur Jean;
    Coureur Paul;
    Coureur Pierre;
    Coureur Jacques;
    List<Integer> resultats = new ArrayList<Integer>();

    Jean = new Coureur("Jean", classement, 0);
    Paul = new Coureur("Paul", classement, 0);
    Pierre = new Coureur("Pierre", classement, 0);
    Jacques = new Coureur("Jacques", classement, 0);

    for (int i = 0; i < 10; i++) {
      Jean = new Coureur("Jean", classement, Jean.nbrPremierePlace);
      Paul = new Coureur("Paul", classement, Paul.nbrPremierePlace);
      Pierre = new Coureur("Pierre", classement, Pierre.nbrPremierePlace);
      Jacques = new Coureur("Jacques", classement, Jacques.nbrPremierePlace);
      Jacques.start();
      Paul.start();
      Pierre.start();
      Jean.start();

      try {
        Jacques.join();
        Paul.join();
        Pierre.join();
        Jean.join();
        classement.resetClassement();
      } catch (Exception e) {
        return;
      }
    }

    System.out.println(Jacques.getStatistiques());
    System.out.println(Paul.getStatistiques());
    System.out.println(Pierre.getStatistiques());
    System.out.println(Jean.getStatistiques());
  }
}
