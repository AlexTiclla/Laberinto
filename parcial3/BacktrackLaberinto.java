/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcial3;

import java.util.LinkedList;

/**
 *
 * @author daniel
 */
public class BacktrackLaberinto {
    
    /// --------- BACKTRACK LABERINTO REY ------------///
    
    public static int vueltas;

    public static boolean backtrackLaberintoRey(int m[][], int i, int j, int ifin, int jfin, int paso) {
        
        m[i][j] = paso;
        
        if (i == ifin && j == jfin) {
            return true;
        }
        
        LinkedList<Regla> L1 = Regla.reglasAplicablesRey(m, i, j);
        
        while (!L1.isEmpty()) {
            // elegirRegla

//            Regla R = (elegirRegla(L1, ifin, jfin));
            Regla R = (L1.removeFirst());
            if (backtrackLaberintoRey(m, R.fil, R.col, ifin, jfin, paso + 1)) {
                return true;
            };
            m[R.fil][R.col] = 0;
            vueltas++;
        }
        return false;
    }

    /// ------------ FIN BACKTRACK LABERINTO REY --------///
    
    

    
    
    
    
    
    public static void mostrar(int m[][]) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j] + ", ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public static void main(String[] args) {
        int m = 6;
        int n = 6;
        int x[][] = new int[m][n];
        
        vueltas = 0;
        
        if (backtrackLaberintoRey(x, 0, 0, 5, 5, 1)) {
            mostrar(x);
            System.out.println("vueltas: " + vueltas);
        } else {
            System.out.println("no hay solucion");
        }
    }

    private static Regla elegirRegla(LinkedList<Regla> L1, int ifin, int jfin) {
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

    private static double distancia(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
