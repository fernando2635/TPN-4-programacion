/**
 * Ejercicio 1: Búsqueda Ternaria
 * Técnica: Divide y Vencerás
 * 
 * La búsqueda ternaria divide el array en tres partes en lugar de dos.
 * Compara el elemento buscado con los elementos en posiciones n/3 y 2n/3.
 * Es más eficiente que la búsqueda binaria en algunos casos.
 */

public class BúsquedaTernaria {
    
    /**
     * Realiza una búsqueda ternaria recursiva en un array ordenado.
     * 
     * @param arr Array ordenado donde buscar
     * @param x Elemento a buscar
     * @param izquierda Índice izquierdo del rango de búsqueda
     * @param derecha Índice derecho del rango de búsqueda
     * @return Índice del elemento si se encuentra, -1 en caso contrario
     */
    public static int búsquedaTernaria(int[] arr, int x, int izquierda, int derecha) {
        // Caso base: rango inválido
        if (izquierda > derecha) {
            return -1;
        }
        
        // Dividir en tres partes
        int tercio1 = izquierda + (derecha - izquierda) / 3;
        int tercio2 = derecha - (derecha - izquierda) / 3;
        
        // Verificar el primer tercio
        if (arr[tercio1] == x) {
            return tercio1;
        }
        
        // Verificar el segundo tercio
        if (arr[tercio2] == x) {
            return tercio2;
        }
        
        // Si x es menor que el primer tercio, buscar en la primera parte
        if (x < arr[tercio1]) {
            return búsquedaTernaria(arr, x, izquierda, tercio1 - 1);
        }
        
        // Si x está entre los dos tercios, buscar en el medio
        if (x > arr[tercio1] && x < arr[tercio2]) {
            return búsquedaTernaria(arr, x, tercio1 + 1, tercio2 - 1);
        }
        
        // Si x es mayor que el segundo tercio, buscar en la tercera parte
        return búsquedaTernaria(arr, x, tercio2 + 1, derecha);
    }
    
    /**
     * Versión iterativa de búsqueda ternaria
     */
    public static int búsquedaTernariaIterativa(int[] arr, int x) {
        int izquierda = 0;
        int derecha = arr.length - 1;
        
        while (izquierda <= derecha) {
            int tercio1 = izquierda + (derecha - izquierda) / 3;
            int tercio2 = derecha - (derecha - izquierda) / 3;
            
            if (arr[tercio1] == x) {
                return tercio1;
            }
            if (arr[tercio2] == x) {
                return tercio2;
            }
            
            if (x < arr[tercio1]) {
                derecha = tercio1 - 1;
            } else if (x > arr[tercio2]) {
                izquierda = tercio2 + 1;
            } else {
                izquierda = tercio1 + 1;
                derecha = tercio2 - 1;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        
        // Prueba recursiva
        System.out.println("=== Búsqueda Ternaria Recursiva ===");
        int x = 7;
        int resultado = búsquedaTernaria(arr, x, 0, arr.length - 1);
        System.out.println("Elemento a buscar: " + x);
        System.out.println("Posición encontrada: " + resultado);
        System.out.println("Elemento encontrado: " + (resultado != -1 ? arr[resultado] : "No encontrado"));
        
        // Prueba iterativa
        System.out.println("\n=== Búsqueda Ternaria Iterativa ===");
        x = 13;
        resultado = búsquedaTernariaIterativa(arr, x);
        System.out.println("Elemento a buscar: " + x);
        System.out.println("Posición encontrada: " + resultado);
        System.out.println("Elemento encontrado: " + (resultado != -1 ? arr[resultado] : "No encontrado"));
        
        // Prueba con elemento no encontrado
        System.out.println("\n=== Prueba: Elemento no existe ===");
        x = 8;
        resultado = búsquedaTernaria(arr, x, 0, arr.length - 1);
        System.out.println("Elemento a buscar: " + x);
        System.out.println("Resultado: " + (resultado == -1 ? "No encontrado" : "Encontrado en posición " + resultado));
    }
}
