/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcial3;

import java.util.LinkedList;

/**
 *
 * @author aticl
 */
public class Parcial3Practica {

    public static int vueltas = 0;

    // Laberinto Rey
    public static boolean laberintoRey(int m[][], int i, int j, int ifin, int jfin, int paso) {
        m[i][j] = paso;
        if (i == ifin && j == jfin) {
            return true;
        }
        LinkedList<Regla> l = reglasAplicablesRey(m, i, j);
        while (!l.isEmpty()) {
            // sin heuristica
//            Regla r = l.removeFirst();

            // con heuristica
            Regla r = elegirMejorRegla2(l, ifin, jfin);

            vueltas++;

            if (laberintoRey(m, r.fil, r.col, ifin, jfin, paso + 1)) return true;

            m[r.fil][r.col] = 0;
        }
        return false;
    }
    
    // Nreinas
 
    public static boolean nReinas(int m[][], int i) {
//        m[i][j] = paso;
        if (i >= m.length) {
            return true;
        }
        LinkedList<Regla> l = reglasAplicables(m, i);

        while (!l.isEmpty()) {
//            Regla r = elegirRegla(l); // Heuristica ==> escoger el de la mitad
            Regla r = l.removeFirst();

            m[r.fil][r.col] = i + 1; // new
            if (nReinas(m, i + 1)) {
                return true;
            }

            m[r.fil][r.col] = 0;
            vueltas++;

        }
        return false;

    }
    
    // Laberinto Caballo -------------------------------------------------------
     public static boolean saltoCaballo(int m[][], int i, int j, int paso) {
        m[i][j] = paso;
        if (paso == m.length * m[0].length) {
            return true;
        }
        LinkedList<Regla> l = reglasAplicablesCaballo(m, i, j);
        while (!l.isEmpty()) {
            // sin heuristica
//            Regla r = l.removeFirst();

            // con heuristica // Por practicar
            Regla r = elegirReglaW(l, m);

            vueltas++;

            if (saltoCaballo(m, r.fil, r.col, paso + 1)) return true;

            m[r.fil][r.col] = 0;
        }
        return false;
    }
     
   
     
        // 1er heuristica interesante Warnsdorff
    private static Regla elegirReglaW(LinkedList<Regla> L1, int[][] m) {
        Regla mejorRegla = null;
        int minMovimientos = Integer.MAX_VALUE;

        for (Regla regla : L1) {
            int posiblesMovimientos = contarMovimientosPosibles(m, regla.fil, regla.col);

            if (posiblesMovimientos < minMovimientos) {
                minMovimientos = posiblesMovimientos;
                mejorRegla = regla;
            }
        }

        // Eliminar la mejor regla seleccionada de la lista y devolverla
        L1.remove(mejorRegla);
        return mejorRegla;
    } 
    // metodo que necesita elegirReglaW
        private static int contarMovimientosPosibles(int[][] m, int i, int j) {
        LinkedList<Regla> posiblesMovimientos = reglasAplicablesCaballo(m, i, j);
        return posiblesMovimientos.size();
    }
    

    private static double distancia(double x1, double y1, double x2, double y2) {
        return (Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));
    }

    public static Regla elegirMejorRegla( LinkedList<Regla> L1, int ifin, int jfin) {
        double distMen = Double.MAX_VALUE;
        int posMenor = 0;
        
        for (int i = 0; i < L1.size(); i++) {
            
            double dist = distancia(L1.get(i).fil, L1.get(i).col, ifin, jfin);
            if (dist < distMen) {
                distMen = dist;
                posMenor = i;
            }
        }
        return L1.remove(posMenor);
 
    }

    private static LinkedList<Regla> reglasAplicablesRey(int[][] m, int i, int j) {
        LinkedList<Regla> l = new LinkedList();
        if (posValida(m, i, j - 1)) {
            l.add(new Regla(i, j - 1));
        }

        if (posValida(m, i - 1, j - 1)) {
            l.add(new Regla(i - 1, j - 1));
        }

        if (posValida(m, i - 1, j)) {
            l.add(new Regla(i - 1, j));
        }

        if (posValida(m, i - 1, j + 1)) {
            l.add(new Regla(i - 1, j + 1));
        }

        if (posValida(m, i, j + 1)) {
            l.add(new Regla(i, j + 1));
        }

        if (posValida(m, i + 1, j + 1)) {
            l.add(new Regla(i + 1, j + 1));
        }

        if (posValida(m, i + 1, j)) {
            l.add(new Regla(i + 1, j));
        }

        if (posValida(m, i + 1, j - 1)) {
            l.add(new Regla(i + 1, j - 1));
        }

        return l;
    }

    private static boolean posValida(int[][] m, int i, int j) {
        return (i >= 0 && i < m.length) && (j >= 0 && j < m[i].length) && (m[i][j] == 0);
    }

    public static void mostrar(int m[][]) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int n = 5;
        int m[][] = new int[n][n];
        if (saltoCaballo(m, 0, 0, 1)) {
            mostrar(m);
            System.out.println("El numero de vueltas es " + vueltas);

        } else {
            System.out.println("no existe solucion");
        }
    }

    private static Regla elegirMejorRegla2(LinkedList<Regla> l, int ifin, int jfin) {
        double distMenor = Double.MAX_VALUE;
        int posMenor = 0;

        for (int i = 0; i < l.size(); i++) {
            double dist = distancia2(l.get(i).fil, l.get(i).col, ifin, jfin);
            if (dist < distMenor) {
                distMenor = dist;
                posMenor = i;

            }
        }
        return l.remove(posMenor);
    }

    private static double distancia2(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private static LinkedList<Regla> reglasAplicables(int[][] m, int i) {
        LinkedList<Regla> l = new LinkedList();
        for (int j = 0; j < m[i].length; j++) {
            if (posValido(m, i, j)) {
                l.add(new Regla(i, j));
            }
        }
        return l;
        
    }

    private static boolean posValido(int[][] m, int i, int j) {
        return filValida(m, i) && colValida(m, j) && diagSupIz(m, i, j)
                && diagSupDer(m, i, j) && diagInfIz(m, i, j) && diagInfDer(m, i, j);
    }

    private static boolean filValida(int[][] m, int i) {
        int j = 0;
        while(j < m[i].length){
            if (m[i][j] != 0) {
                return false;
            }
            j++;
        }
        return true;
    }

    private static boolean colValida(int[][] m, int j) {
  int i = 0;
        while(i < m.length){
            if (m[i][j] != 0) {
                return false;
            }
            i++;
        }
        return true;    }

    private static boolean diagSupIz(int[][] m, int i, int j) {
//        int i1 = i;
//        int j1 = j;
        while(i >= 0 && j>= 0){
            if (m[i][j] != 0) {
                return false;
            }
            j--;
            i--;
        }
        return true;    }

    private static boolean diagSupDer(int[][] m, int i, int j) {
              while(i >= 0 && j < m[i].length){
            if (m[i][j] != 0) {
                return false;
            }
            j++;
            i--;
        }
        return true;
    }

    private static boolean diagInfIz(int[][] m, int i, int j) {
              while(i < m.length && j >= 0 ){
            if (m[i][j] != 0) {
                return false;
            }
            j--;
            i++;
        }
        return true;    
    }

    private static boolean diagInfDer(int[][] m, int i, int j) {
              while(i < m.length && j < m[i].length ){
            if (m[i][j] != 0) {
                return false;
            }
            j++;
            i++;
        }
        return true;  
    }

    private static Regla elegirRegla(LinkedList<Regla> l) {
        return l.remove(l.size()/2);
    }

    private static LinkedList<Regla> reglasAplicablesCaballo(int[][] m, int i, int j) {
           LinkedList<Regla> l = new LinkedList();
        if (posValida(m, i+1, j-2)) l.add(new Regla(i+1, j-2));
        if (posValida(m, i-1, j-2)) l.add(new Regla(i-1, j-2));
        
        if (posValida(m, i-2, j+1)) l.add(new Regla(i-2, j+1));
        if (posValida(m, i-2, j-1)) l.add(new Regla(i-2, j-1));
        
        if (posValida(m, i-1, j+2)) l.add(new Regla(i-1, j+2));
        if (posValida(m, i+1, j+2)) l.add(new Regla(i+1, j+2));
        
        if (posValida(m, i+2, j-1)) l.add(new Regla(i+2, j-1));
        if (posValida(m, i+2, j+1)) l.add(new Regla(i+2, j+1));
        
        return l;
    }

}
