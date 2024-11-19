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
public class nReina {
    
    /// ------------------ N REINAS -------------------- ///
    
    public static int vueltas;

      public static boolean nReinas(int m[][], int i) {
        
//        m[i][j] = paso;
        
        if (i >= m.length) {
            return true;
        }
        
        LinkedList<Regla> L1 = reglasAplicables(m, i);
        
        while (!L1.isEmpty()) {
            // elegirRegla

//            Regla R = (elegirRegla(L1, ifin, jfin));
            Regla R = elegirReglaB(L1);
            m[R.fil][R.col] = i + 1;
            if (nReinas(m, i + 1)) {
                return true;
            };
            m[R.fil][R.col] = 0;
            vueltas++;
        }
        return false;
    }

    public static LinkedList<Regla> reglasAplicables(int m[][], int i) {
        LinkedList<Regla> L1 = new LinkedList();
        
        for (int j = 0; j < m[i].length; j++) { // recorrer toda las columnas => m[i].length
            
            if (posValido(m, i, j)) {
                L1.add(new Regla(i, j));
            }
        }
        return L1;
    }

    public static boolean posValido(int m[][], int i, int j) {
        return filValido(m, i) && colValido(m, j)
                && diagSupIzq(m, i, j) && diagSupDer(m, i, j)
                && diagInfIzq(m, i, j) && diagInfDer(m, i, j);
    }

    public static boolean filValido(int m[][], int i) {
        
        int j = 0;
        
        while (j < m[i].length) {
            
            if (m[i][j] != 0) { // aqui
                
                return false;
            }
            
            j++;
            
        }
        
        return true;
    }

    public static boolean colValido(int m[][], int j) {
        int i = 0;
        
        while (i < m.length) {
            
            if (m[i][j] != 0) {
                
                return false;
                
            }
            
            i++;
            
        }
        
        return true;
        
    }

    public static boolean diagSupIzq(int m[][], int i1, int j1) {
        int i = i1, j = j1;
        
        while (i >= 0 && j >= 0) {
            
            if (m[i][j] != 0) {
                return false;
            }
            
            i--;
            j--;
            
        }
        return true;
    }

    public static boolean diagSupDer(int m[][], int i1, int j1) {
        int i = i1, j = j1;
        while (i >= 0 && j < m[i1].length) {
            
            if (m[i][j] != 0) {
                return false;
            }
            i = i - 1;
            j = j + 1;
        }
        return true;
    }

    public static boolean diagInfIzq(int m[][], int i1, int j1) {
        int i = i1, j = j1;
        while (i < m.length && j >= 0) {
            
            if (m[i][j] != 0) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static boolean diagInfDer(int m[][], int i1, int j1) {
        int i = i1, j = j1;
        while (i < m.length && j < m[i1].length) {
            
            if (m[i][j] != 0) {
                return false;
            }
            i++;
            j++;
        }
        return true;
    }
    
    /// --------------- FIN N REINAS ------------------------///
    
    
    
    // ----------------HEURISTICAS------------------/// 
    

    public static Regla elegirReglaA(LinkedList<Regla> L1) {
        return L1.removeFirst();
    }

    public static Regla elegirReglaB(LinkedList<Regla> L1) {
        return L1.remove(L1.size() / 2);
    }

    // --------- regla de los pesos --------- ///
    private static Regla elegirReglaPesos(int m[][], int k, LinkedList<Regla> L1) {
        int i = 0;
        int pMenorPeso = 0;
        int menor = Integer.MAX_VALUE;
        if (k != m.length - 1) {
            while (i < L1.size()) {
                Regla R = L1.get(i);
                m[R.fil][R.col] = 1;
                int cantPosiciones = reglasAplicables(m, R.fil + 1).size();
                if (cantPosiciones < menor) {
                    menor = cantPosiciones;
                    pMenorPeso = i;
                }
                m[R.fil][R.col] = 0;
                i++;
            }
        }
        return L1.remove(pMenorPeso);
    }

    
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

        if (nReinas(x, 0)) {
            mostrar(x);
            System.out.println("vueltas: " + vueltas);
        } else {
            System.out.println("no hay solucion");
        }

    }
}
