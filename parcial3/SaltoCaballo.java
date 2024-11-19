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
public class SaltoCaballo {
    
    
    
    ///-------------------- SALTO DEL CABALLO -------------------///
    
    public static int vueltas;
    
    public static boolean saltoDelCaballo(int m[][], int i, int j, int paso) {
        m[i][j] = paso;
        if (TodoVisitado(m)) {
            return true;
        }
        LinkedList<Regla> L1 = Regla.reglasAplicablesCaballo(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirHeuristica1(m, L1); //SELECCIONAR HEURISTICA
            if (saltoDelCaballo(m, R.fil, R.col, paso + 1)) {
                return true;
            }
            vueltas++;
            m[R.fil][R.col] = 0;
        }
        return false;
    }
    
    private static boolean TodoVisitado(int[][] m) {
        for (int[] ints : m) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    ///-------------------- FIN SALTO DEL CABALLO -------------------///
    
    
    
    
    
    
    // -------------ESCRIBIR LAS 2 HEURISTICAS (Reemplazar a L1.removeFirst();)
    
    //heuristica 1: elegir la posiciones que tengan menos posibilidades
    public static Regla elegirHeuristica1(int m[][], LinkedList<Regla> l) {
        int index = 0;
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < l.size(); k++) {
            int x = (Regla.reglasAplicablesCaballo(m, l.get(k).fil, l.get(k).col)).size();
            if (x < min) {
                min = x;
                index = k;
            }
        }
        return l.remove(index);
    }
    
    
    //heuristica 2: matriz de costos 
    public static void ponerPrecio(int m[][]) {
        int[][] a = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] = (Regla.reglasAplicablesCaballo(a, i, j)).size();
            }
        }
    }

    public static Regla elegirHeuristica2(int m[][], LinkedList<Regla> l) {
        int index = 0;
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < l.size(); k++) {
            int x = m[l.get(k).fil][l.get(k).col];
            if (x < min) {
                min = x;
                index = k;
            }
        }
        return l.remove(index);
    }
    
    
    ///---------- FIN HEURISTICAS ----------- ///
    
    
    
    
    
    
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
        
        if (saltoDelCaballo(x, 0, 0, 1)) {
            mostrar(x);
            System.out.println("vueltas: " + vueltas);
        } else {
            System.out.println("no hay solucion");
        }
    }
}
