import java.util.*;

public class Oficina{
    private int tamaño = 4;
    private char[][] habitacion;
    private List<String> soluciones;
    
    private static final char ESCRITORIO = 'E';
    private static final char SILLA = 'S';
    private static final char VACIO = '.';
    
    public DisenoOficinaBasico() {
        this.habitacion = new char[tamaño][tamaño];
        this.soluciones = new ArrayList<>();
        inicializarHabitacion();
    }
    // armar la habitacion segun el tamaño seteado
    private void inicializarHabitacion() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                habitacion[i][j] = VACIO;
            }
        }
    }
    
    public List<String> disenarOficina(int numEscritorios, int numSillas) {
        soluciones.clear();
        backtrackElementos(0, numEscritorios, ESCRITORIO, numSillas);
        return soluciones;
    }
    
    private void backtrackElementos(int escritoriosColocados, int totalEscritorios, 
                                   char tipoActual, int sillasRestantes) {
        // Si terminamos con escritorios, pasar a sillas
        if (escritoriosColocados == totalEscritorios && tipoActual == ESCRITORIO) {
            backtrackElementos(0, sillasRestantes, SILLA, 0);
            return;
        }
        
        // Si terminamos con sillas, guardar solución
        if (tipoActual == SILLA && escritoriosColocados == sillasRestantes) {
            soluciones.add(crearSolucionString());
            return;
        }
        
        // Probar cada posición (Backtracking Tipo 2)
        for (int fila = 0; fila < tamaño; fila++) {
            for (int columna = 0; columna < tamaño; columna++) {
                if (puedeColocar(fila, columna, tipoActual)) {
                    habitacion[fila][columna] = tipoActual;
                    
                    if (tipoActual == ESCRITORIO) {
                        backtrackElementos(escritoriosColocados + 1, totalEscritorios, 
                                         tipoActual, sillasRestantes);
                    } else {
                        backtrackElementos(escritoriosColocados + 1, sillasRestantes, 
                                         tipoActual, 0);
                    }
                    
                    habitacion[fila][columna] = VACIO; // Backtrack
                }
            }
        }
    }
    
    private boolean puedeColocar(int fila, int columna, char tipoElemento) {
        if (habitacion[fila][columna] != VACIO) {
            return false;
        }
        
        // Restricciones del problema: no misma fila/columna
        for (int i = 0; i < tamaño; i++) {
            if (habitacion[fila][i] == tipoElemento) return false; // Misma fila
            if (habitacion[i][columna] == tipoElemento) return false; // Misma columna
        }
        return true;
    }
    
    private String crearSolucionString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                sb.append(habitacion[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        DisenoOficinaBasico diseño = new DisenoOficinaBasico();
        List<String> soluciones = diseño.disenarOficina(2, 2);
        
        System.out.println("Soluciones encontradas: " + soluciones.size());
        for (int i = 0; i < Math.min(5, soluciones.size()); i++) {
            System.out.println("Solución " + (i + 1) + ":");
            System.out.println(soluciones.get(i));
        }
    }
}
