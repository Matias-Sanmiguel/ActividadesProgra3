import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        int[] numeros = {2, 3, 5};
        ArrayList<Integer> combinacion_actual = new ArrayList<>();
        combinacion(numeros, 0, combinacion_actual);
    }

    private static void combinacion(int[] numeros, int i, ArrayList<Integer> combinacion_actual) {
        if (i == numeros.length) {
            int suma = 0;
            for (int e : combinacion_actual) {
                suma += e;
            }
            if (suma == 5) {
                for (int e : combinacion_actual) {
                    System.out.print(e + " ");
                }
                System.out.println("\n----");
            }
            return;
        }
        combinacion_actual.add(numeros[i]);
        combinacion(numeros, i + 1, combinacion_actual);

        combinacion_actual.removeLast();
        combinacion(numeros, i + 1, combinacion_actual);
    }
}