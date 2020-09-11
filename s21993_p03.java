package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class s21993_p03 {
    public static String sciezka = "C:\\Users\\aniut\\IdeaProjects\\s21993_p03.java\\wilki.txt";
    public static void main(String[] args) {
        try {
            class Ssak {
                private String imie;
                private short rokUrodzenia;
                private boolean mlody;

                String przedstawSie() {
                    String info = (imie + "," + rokUrodzenia + "," + mlody);
                    return info;
                }

                void zapisz(FileOutputStream outputStream)  {
                    try
                    {
                        for (int i = 0; i < przedstawSie().length(); i++) {
                            outputStream.write(przedstawSie().charAt(i));
                        }
                    }
                    catch (IOException e) {

                    }
                }
            }

            class Wadera extends Ssak {
                private int iloscSzczeniat;
                private Ssak[] szczenieta;

                public Wadera(int iloscSzczeniat, Ssak[] szczenieta) {
                    this.iloscSzczeniat = iloscSzczeniat;
                    this.szczenieta = new Ssak[iloscSzczeniat];
                }
                String przedstawSie() {
                    String info = (super.imie + "," + super.rokUrodzenia + "," + super.mlody + "," + iloscSzczeniat + "," + szczenieta);
                    return info;
                }
                void zapisz(FileOutputStream outputStream)  {
                    try
                    {
                        for (int i = 0; i < przedstawSie().length(); i++) {
                            outputStream.write(przedstawSie().charAt(i));
                        }
                    }
                    catch (IOException e) {
                    }
                }
            }
            class Wilk extends Ssak {
                private String nazwaStada;
                private int pozycja;

                public Wilk(String nazwaStada, int pozycja) {
                    this.nazwaStada = nazwaStada;
                    this.pozycja = pozycja;
                }

                String przedstawSie() {
                    String info = (super.imie + "," + super.rokUrodzenia + "," + super.mlody + "," + nazwaStada + "," + pozycja);
                    return info;
                }

                void zapisz(FileOutputStream outputStream)  {
                    try
                    {
                        for (int i = 0; i < przedstawSie().length(); i++) {
                            outputStream.write(przedstawSie().charAt(i));
                        }
                    }
                    catch (IOException e) {
                    }
                }
            }
            if (new File(sciezka).exists()) {
                zaladuj(sciezka);
            } else {
                FileOutputStream fileOutputStream = new FileOutputStream("wilki.txt");
                // I przykład
                Ssak[] pierwszeStado = new Ssak[24];
                for (int i = 0; i <= pierwszeStado.length - 1; i++) {
                    if (i % 2 == 0) {
                        pierwszeStado[i] = new Wilk("pierwszeStado", i);
                        pierwszeStado[i].imie = "Wilk";
                        pierwszeStado[i].rokUrodzenia = (short) (Math.random() % 7 + 2000);
                        pierwszeStado[i].mlody = false;
                        if (i % 6 == 0) {
                            pierwszeStado[i].mlody = true; //4 młode wilki
                        }
                        System.out.println((pierwszeStado[i].przedstawSie()));
                        pierwszeStado[i].zapisz(fileOutputStream);
                    }
                    if (i % 2 == 1) {
                        Ssak[] szczenieta = new Ssak[24]; //bedzie ich przynajmniej 8
                        szczenieta[i] = new Wadera(0, null); //ponieważ mloda wadera jeszcze nie ma szczeniat
                        szczenieta[i].imie = "Szczenie";
                        szczenieta[i].mlody = true;
                        szczenieta[i].rokUrodzenia = (short) 2002;
                        String szczenie = szczenieta[i].przedstawSie();
                        pierwszeStado[i] = new Wadera(2, szczenieta);
                        pierwszeStado[i].imie = "Wilczyca";
                        pierwszeStado[i].rokUrodzenia = 2000;
                        pierwszeStado[i].mlody = false;
                        System.out.println(szczenie);
                        System.out.println(pierwszeStado[i].przedstawSie());
                        //System.out.println(pierwszeStado[i].przedstawSie() + szczenie);
                        pierwszeStado[i].zapisz(fileOutputStream);
                    }
                }
                //II przykład
                Ssak[] drugieStado = new Ssak[24];
                for (int i = 0; i <= drugieStado.length - 1; i++) {
                    if (i % 2 == 0) {
                        drugieStado[i] = new Wilk("drugieStado", i);
                        drugieStado[i].zapisz(fileOutputStream);
                    }
                    if (i % 2 == 1) {
                        Ssak[] szczenieta = new Ssak[24]; //bedzie ich przynajmniej 8
                        szczenieta[i] = new Ssak();
                        szczenieta[i].imie = "Szczenie" + (int) Math.random();
                        szczenieta[i].mlody = true;
                        szczenieta[i].rokUrodzenia = (short) 2000;
                        drugieStado[i] = new Wadera(2, szczenieta);
                        drugieStado[i].zapisz(fileOutputStream);
                    }
                }
            }
        }
        catch(IOException e) {

        }
    }
    class Ssak {
        private String imie;
        private short rokUrodzenia;
        private boolean mlody;

        String przedstawSie() {
            String info = (imie + "," + rokUrodzenia + "," + mlody);
            return info;
        }

        void zapisz(FileOutputStream outputStream)  {
            try
            {
                for (int i = 0; i < przedstawSie().length(); i++) {
                    outputStream.write(przedstawSie().charAt(i));
                }
            }
            catch (IOException e) {

            }
        }
    }
    public static Main.Ssak[] zaladuj(String sciezka){
        Main.Ssak[] ssaki = new Main.Ssak[24];
        try {
            Scanner odczyt = new Scanner(new File(sciezka));
            int i=0;
            while (odczyt.hasNextInt()) {
                ssaki[i].imie = odczyt.next();
                ssaki[i].mlody = odczyt.nextBoolean();
                ssaki[i].rokUrodzenia = odczyt.nextShort();
                System.out.print( ssaki[i] + " " );
                i++;
            }
            odczyt.close();
        } catch (FileNotFoundException e) { }
        return ssaki ;
    }
}
