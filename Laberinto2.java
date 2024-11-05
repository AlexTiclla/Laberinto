package parcial22;

import java.util.LinkedList;

public class Laberinto2 {
    //---------------------- Reglas Aplicables ---------------------------------

    public static LinkedList<Regla> reglasAplicablesRey(int m[][], int i, int j) {
        LinkedList<Regla> r = new LinkedList();
        if (posValida(m, i, j - 1)) {
            r.add(new Regla(i, j - 1));
        }

        if (posValida(m, i - 1, j - 1)) {
            r.add(new Regla(i - 1, j - 1));
        }

        if (posValida(m, i - 1, j)) {
            r.add(new Regla(i - 1, j));
        }

        if (posValida(m, i - 1, j + 1)) {
            r.add(new Regla(i - 1, j + 1));
        }
        if (posValida(m, i, j + 1)) {
            r.add(new Regla(i, j + 1));
        }

        if (posValida(m, i + 1, j + 1)) {
            r.add(new Regla(i + 1, j + 1));
        }

        if (posValida(m, i + 1, j)) {
            r.add(new Regla(i + 1, j));
        }

        if (posValida(m, i + 1, j - 1)) {
            r.add(new Regla(i + 1, j - 1));
        }

        return r;
    }

    public static LinkedList<Regla> reglasAplicablesCaballo(int m[][], int i, int j) {
        LinkedList<Regla> r = new LinkedList();
        if (posValida(m, i - 1, j - 2)) {
            r.add(new Regla(i - 1, j - 2));
        }

        if (posValida(m, i + 1, j - 2)) {
            r.add(new Regla(i + 1, j - 2));
        }

        if (posValida(m, i - 2, j - 1)) {
            r.add(new Regla(i - 2, j - 1));
        }

        if (posValida(m, i - 2, j + 1)) {
            r.add(new Regla(i - 2, j + 1));
        }

        if (posValida(m, i - 1, j + 2)) {
            r.add(new Regla(i - 1, j + 2));
        }

        if (posValida(m, i + 1, j + 2)) {
            r.add(new Regla(i + 1, j + 2));
        }

        if (posValida(m, i + 2, j - 1)) {
            r.add(new Regla(i + 2, j - 1));
        }

        if (posValida(m, i + 2, j + 1)) {
            r.add(new Regla(i + 2, j + 1));
        }

        return r;
    }

    public static LinkedList<Regla> reglasAplicablesTorre(int m[][], int i, int j) {
        LinkedList<Regla> r = new LinkedList();

        int k = j - 1;
        while (posValida(m, i, k)) {
            r.add(new Regla(i, k));
            k--;
        }

        k = i - 1;
        while (posValida(m, k, j)) {
            r.add(new Regla(k, j));
            k--;
        }

        k = j + 1;
        while (posValida(m, i, k)) {
            r.add(new Regla(i, k));
            k++;
        }

        k = i + 1;
        while (posValida(m, k, j)) {
            r.add(new Regla(k, j));
            k++;
        }

        return r;
    }

    public static LinkedList<Regla> reglasAplicablesAlfil(int m[][], int i, int j) {
        LinkedList<Regla> r = new LinkedList();

        int y = j - 1;
        int x = i - 1;
        while (posValida(m, x, y)) {
            r.add(new Regla(x, y));
            x--;
            y--;
        }

        x = i - 1;
        y = j + 1;
        while (posValida(m, x, y)) {
            r.add(new Regla(x, y));
            x--;
            y++;
        }

        x = i + 1;
        y = j + 1;
        while (posValida(m, x, y)) {
            r.add(new Regla(x, y));
            x++;
            y++;
        }

        x = i + 1;
        y = j - 1;
        while (posValida(m, x, y)) {
            r.add(new Regla(x, y));
            x++;
            y--;
        }

        return r;
    }

    public static LinkedList<Regla> reglasAplicablesReina(int m[][], int i, int j) {
        LinkedList<Regla> r = new LinkedList();

        r.addAll(reglasAplicablesTorre(m, i, j));
        r.addAll(reglasAplicablesAlfil(m, i, j));

        return r;
    }
    //--------------------------------------------------------------------------
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

public static boolean diagSupIzq(int m[][], int i1, int j1) {
    int i = i1, j = j1;
    while (i >= 0 && j >= 0) {
        if (m[i][j] != 0) {
            return false;
        }
        i = i - 1;
        j = j - 1;
    }
    return true;
}


public static boolean diagInfIzq(int m[][], int i1, int j1) {
    int i = i1, j = j1;
    while (i < m.length && j >= 0) {
        if (m[i][j] != 0) {
            return false;
        }
        i = i + 1;
        j = j - 1;
    }
    return true;
}

public static boolean diagInfDer(int m[][], int i1, int j1) {
    int i = i1, j = j1;
    while (i < m.length && j < m[i].length) {
        if (m[i][j] != 0) {
            return false;
        }
        i = i + 1;
        j = j + 1;
    }
    return true;
}


    private static boolean posicionDisponible(int[][] m, int i, int j) {
        return filValido(m, i) && colValido(m, j) &&
diagSupIzq(m, i, j) && diagSupDer(m, i, j) &&
diagInfIzq(m, i, j) && diagInfDer(m, i, j);

    }

    private static boolean filValido(int[][] m, int i) {
        int j = 0;
        while (j < m.length){
            if (m[i][j] != 0) {
                return false;
            }
            j++;
        }
        return true;
        
    }
        private static boolean colValido(int[][] m, int j) {
        int i = 0;
        while (i < m.length){
            if (m[i][j] != 0) {
                return false;
            }
            i++;
        }
        return true;
        
    }

    public static class Regla {

        public int fil;
        public int col;

        public Regla(int fil, int col) {
            this.fil = fil;
            this.col = col;
        }
    }

    public static Regla elegirMejorRegla(int[][] m, LinkedList<Regla> L1, int ifin, int jfin) {
        int i = 0;
        double distMen = Double.MAX_VALUE;
        int posRegla = 0;
        while (i < L1.size()) {
            double dist = distancia(L1.get(i).fil, L1.get(i).col, ifin, jfin);
            if (dist < distMen) {
                distMen = dist;
                posRegla = i;
            }
            i++;
        }
        return (L1.remove(posRegla));
    }

    private static double distancia(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private static boolean posValida(int[][] m, int i, int j) {
        return (i >= 0 && i < m.length && j >= 0 && j < m[i].length && m[i][j] == 0);
    }

    private static void mostrar(int[][] m) {
        for (int[] fila : m) {
            for (int celda : fila) {
                System.out.print(celda + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    // ------------------------- Sin Heuristica --------------------------------
    //---------------- LABERINTO DEL REY --------------------///
    public static void LaberintoRey(int m[][], int i, int j, int ifin, int jfin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == ifin && j == jfin) {
            mostrar(m);
        }
        LinkedList<Regla> L1 = reglasAplicablesRey(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = L1.removeFirst();   // Elige la 1ra Regla y elimina
            LaberintoRey(m, R.fil, R.col, ifin, jfin, paso + 1);
            m[R.fil][R.col] = 0;

        }
    }

    //---------------- LABERINTO DEL CABALLO --------------------///
    public static void LaberintoCaballo(int m[][], int i, int j, int ifin, int jfin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == ifin && j == jfin) {
            mostrar(m);
        }
        LinkedList<Regla> L1 = reglasAplicablesCaballo(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = L1.removeFirst();   // Elige la 1ra Regla y elimina
            LaberintoCaballo(m, R.fil, R.col, ifin, jfin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    //---------------- LABERINTO DE LA TORRE --------------------///
    public static void LaberintoTorre(int m[][], int i, int j, int ifin, int jfin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == ifin && j == jfin) {
            mostrar(m);
        }
        LinkedList<Regla> L1 = reglasAplicablesTorre(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = L1.removeFirst();   // Elige la 1ra Regla y elimina
            LaberintoTorre(m, R.fil, R.col, ifin, jfin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    //---------------- LABERINTO DEL ALFIL --------------------///
    public static void LaberintoAlfil(int m[][], int i, int j, int ifin, int jfin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == ifin && j == jfin) {
            mostrar(m);
        }
        LinkedList<Regla> L1 = reglasAplicablesAlfil(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = L1.removeFirst();   // Elige la 1ra Regla y elimina
            LaberintoAlfil(m, R.fil, R.col, ifin, jfin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }

    //---------------- LABERINTO DEL REINA --------------------///
    public static void LaberintoReina(int m[][], int i, int j, int ifin, int jfin, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (i == ifin && j == jfin) {
            mostrar(m);
        }
        LinkedList<Regla> L1 = reglasAplicablesReina(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = L1.removeFirst();   // Elige la 1ra Regla y elimina
            LaberintoReina(m, R.fil, R.col, ifin, jfin, paso + 1);
            m[R.fil][R.col] = 0;
        }
    }
    // -------------------------------------------------------------------------

    private static boolean backtrack(int[][] m, int i, int j, int ifin, int jfin, int paso) {
       m[i][j] = paso; // Marcar la posici n actual
                    mostrar(m); // Mostrar el tablero cada vez que el rey se mueve

        if (i == ifin && j == jfin) {

            return true; // Se ha encontrado el destino
        }

        //
        LinkedList<Regla> L1 = reglasAplicablesRey(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirMejorRegla(m, L1, ifin, jfin);

            if (backtrack(m, R.fil, R.col, ifin, jfin, paso + 1)) return true; // Si se encuentra un camino
            
            m[R.fil][R.col] = 0; // Desmarcar la posici n si no lleva al destino
        }
        return false; // No se encontr  camino
    }
    
     

    public static Regla elegirRegla(LinkedList<Regla> L1) {
        return L1.removeFirst();
    }

    public static Regla elegirReglaH( LinkedList<Regla> L1) {
        return L1.remove(L1.size() / 2);
    }

//    public static boolean posicionDisponible(int[][] m, int i, int j) {
//        for (int k = 0; k < m.length; k++) {
//            for (int l = 0; l < m.length; l++) {
//                if ((m[k][l] != 0 && m[k][l] != -1)) {
//                    if (k == i) {
//                        return false;
//                    }
//                    if (j == l) {
//                        return false;
//                    }
//                    int kt = k + 1;
//                    int lt = l + 1;
//                    while (kt < m.length && lt < m[k].length) {
//                        if (kt == i && lt == j) {
//                            return false;
//                        }
//                        kt++;
//                        lt++;
//                    }
//                    kt = k + 1;
//                    lt = l - 1;
//                    while (kt < m.length && lt < m[k].length) {
//                        if (kt == i && lt == j) {
//                            return false;
//                        }
//                        kt++;
//                        lt--;
//                    }
//                    kt = k - 1;
//                    lt = l - 1;
//                    while (kt < m.length && lt < m[k].length) {
//                        if (kt == i && lt == j) {
//                            return false;
//                        }
//                        kt--;
//                        lt--;
//                    }
//                    kt = k - 1;
//                    lt = l + 1;
//                    while (kt < m.length && lt < m[k].length) {
//                        if (kt == i && lt == j) {
//                            return false;
//                        }
//                        kt--;
//                        lt++;
//                    }
//                }
//            }
//        }
//        return true;
//
//    }

    public static LinkedList<Regla> reglasAplicables(int[][] m, int i) {
        LinkedList<Regla> L1 = new LinkedList();

        for (int j = 0; j < m[i].length; j++) {
            if (posicionDisponible(m, i, j)) {
                L1.add(new Regla(i, j));
            }

        }
        return L1;
    }
    public static long counter = 0;
    public static boolean nReinas(int[][] m, int paso) {
        if (paso > m.length) return true;
        
        LinkedList L1 = reglasAplicables(m, paso - 1);
//        System.out.println(L1);
        while (!L1.isEmpty()) {
            Regla R = elegirReglaH(L1);
            m[R.fil][R.col] = paso;
            if (nReinas(m, paso + 1)) return true;
            
            m[R.fil][R.col] = 0;
            counter++;
        }
        return false;
    }

    // ------------------------- Con Heuristica --------------------------------
    public static boolean laberintoReyBackTrack(int[][] m, int i, int j, int ifin, int jfin, int paso) {
        return backtrack(m, i, j, ifin, jfin, paso); // Llama al m todo backtrack
    }

    public static boolean laberintoCaballoBackTrack(int[][] m, int i, int j, int ifin, int jfin, int paso) {
        return backtrack(m, i, j, ifin, jfin, paso); // Llama al m todo backtrack
    }

    public static boolean laberintoTorreBackTrack(int[][] m, int i, int j, int ifin, int jfin, int paso) {
        return backtrack(m, i, j, ifin, jfin, paso); // Llama al m todo backtrack
    }

    public static boolean laberintoAlfilBackTrack(int[][] m, int i, int j, int ifin, int jfin, int paso) {
        return backtrack(m, i, j, ifin, jfin, paso); // Llama al m todo backtrack
    }

    public static boolean laberintoReinaBackTrack(int[][] m, int i, int j, int ifin, int jfin, int paso) {
        return backtrack(m, i, j, ifin, jfin, paso); // Llama al m todo backtrack
    }

    public static void main(String[] args) {
//        int[][] m = new int[10][10]; // Crear un tablero 3x3
//        m[3][1] = -1;
//        m[3][2] = -1;
//        m[3][3] = -1;
//        m[3][4] = -1;
//        if (!laberintoReyBackTrack(m, 2, 3, 9, 3, 1)) {
//            System.out.println("No se encontr  un camino.");
//        }

        int n = 30;
        int m[][] = new int[n][n];
        if (nReinas(m, 1)) {
            mostrar(m);
            System.out.println("La cantidad de vueltas es:" + counter);
        } else {
            System.out.println("Sin solución");
        }
    }
}
