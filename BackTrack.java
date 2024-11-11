package Test;

import java.util.LinkedList;

public class BackTrack {

    public static long counter = 0;

    private static boolean backtrack(int[][] m, int i, int j, int paso) {
        m[i][j] = paso;

        // Verificar si el número de pasos es igual al número de casillas en el tablero
        if (paso == m.length * m[0].length) {
            return true;  // Se visitaron todas las casillas
        }

        LinkedList<Regla> L1 = reglasAplicablesCaballo(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirReglaW(L1, m);

            // Realizar el siguiente movimiento
            if (backtrack(m, R.fil, R.col, paso + 1)) {
                return true;
            }

            // Retroceder
            m[R.fil][R.col] = 0;
            counter++;
        }

        return false;
    }

    private static Regla elegirRegla(LinkedList<Regla> L1) {
        return L1.removeFirst();  // Elegir la primera regla sin heurística
    }

    private static Regla elegirReglaH(LinkedList<Regla> L1) {
        return L1.remove(L1.size() / 2);  // Elegir la primera regla sin heurística
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

// Método auxiliar para contar los movimientos posibles desde una posición específica
    private static int contarMovimientosPosibles(int[][] m, int i, int j) {
        LinkedList<Regla> posiblesMovimientos = reglasAplicablesCaballo(m, i, j);
        return posiblesMovimientos.size();
    }
// Heuristica por cercania al centro

    private static Regla elegirReglaC(LinkedList<Regla> L1, int[][] m) {
        Regla mejorRegla = null;
        double minDistanciaCentro = Double.MAX_VALUE;

        // Calcular el centro del tablero
        int centroFil = m.length / 2;
        int centroCol = m[0].length / 2;

        for (Regla regla : L1) {
            // Calcular la distancia al centro para cada movimiento posible
            double distanciaCentro = distancia(regla.fil, regla.col, centroFil, centroCol);

            // Elegir el movimiento con menor distancia al centro
            if (distanciaCentro < minDistanciaCentro) {
                minDistanciaCentro = distanciaCentro;
                mejorRegla = regla;
            }
        }

        // Eliminar la mejor regla seleccionada de la lista y devolverla
        L1.remove(mejorRegla);
        return mejorRegla;
    }

// Método para calcular la distancia euclidiana entre dos puntos
    private static double distancia(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private static boolean sudoku(int[][] m, int i, int j) {

        if (i >= m.length) {
            return true;
        }
        if (j >= m[i].length) {
            return sudoku(m, i + 1, 0);
        }
        if (m[i][j] != 0) {
            return sudoku(m, i, j + 1);
        }

        LinkedList<Integer> L1 = reglasAplicables(m, i, j);
        while (!L1.isEmpty()) {
            m[i][j] = L1.removeFirst();
            if (sudoku(m, i, j + 1)) {
                return true;
            }
            m[i][j] = 0;
        }
        return false;
    }

    private static LinkedList<Integer> reglasAplicables(int[][] m, int i, int j) {
        LinkedList<Integer> l = new LinkedList();
        for (int valor = 1; valor <= m.length; valor++) {
            if (!enFila(m, i, valor) && !enColumna(m, j, valor)
                    && !enRegion(m, i, j, valor)) {
                l.add(valor);
            }
        }
        return l;

    }

    private static boolean enFila(int[][] m, int i, int valor) {

        for (int j = 0; j < m[i].length; j++) {
            if (m[i][j] == valor) {
                return true;
            }
        }
        return false;
    }

    private static boolean enColumna(int[][] m, int j, int valor) {
        for (int i = 0; i < m.length; i++) {
            if (m[i][j] == valor) {
                return true;
            }
        }
        return false;
    }

    private static boolean enRegion(int[][] m, int i, int j, int valor) {
        int nfil = (int)Math.sqrt(m.length);
        int ncol = (int)Math.sqrt(m[i].length);

        int iregion = (i / nfil) * nfil;
        int jregion = (j / ncol) * ncol;

        for (int a = iregion; a < iregion + nfil; a++) {
            for (int b = jregion; b < jregion + ncol; b++) {
                if (m[a][b] == valor) {
                    return true;
                }
            }
        }
        return false;

    }

    public static class Regla {

        public int fil;
        public int col;

        public Regla(int fil, int col) {
            this.fil = fil;
            this.col = col;
        }
    }

    public static LinkedList<Regla> reglasAplicablesCaballo(int m[][], int i, int j) {
        LinkedList<Regla> r = new LinkedList<>();
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

    private static boolean posValida(int[][] m, int i, int j) {
        return (i >= 0 && i < m.length && j >= 0 && j < m[i].length && m[i][j] == 0);
    }

    private static void mostrar(int[][] m) {
        for (int[] row : m) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] m = new int[4][4]; // Crear un tablero de 8x8
        if (sudoku(m, 0, 0)) {  // Comienza desde la posición (0,0)
            System.out.println("Hay solucion ");
        } else {
            System.out.println("No se encontró solucion.");
        }
        mostrar(m);
    }
}
