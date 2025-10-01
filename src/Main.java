import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[] numeros = {1,2,3};
        ArrayList<Integer> combinacion_actual = new ArrayList<>();
        combinacion(numeros, 0, combinacion_actual);



    }

    private static void combinacion(int[] numeros, int i, ArrayList<Integer> combinacion_actual) {
        if(numeros.length == i){
            combinacion_actual.forEach(e-> System.out.println(e + " "));
            System.out.println("----");
            return;
        }
        combinacion_actual.add(numeros[i]);
        combinacion(numeros,i+1, combinacion_actual);

        //truco backtracking
        combinacion_actual.removeLast();
        combinacion(numeros, i+1, combinacion_actual);
    }
}
