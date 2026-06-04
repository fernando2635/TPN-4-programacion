/**
 * Ejercicio 2: Encontrar el Máximo Subarray
 * Técnica: Divide y Vencerás (Algoritmo de Kadane)
 * 
 * Dado un array de enteros (positivos y negativos), encontrar el subarray contiguo
 * que tiene la suma máxima.
 * 
 * Ejemplo: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 * Resultado: 6 (subarray [4, -1, 2, 1])
 */

public class MáximoSubarray {
    
    /**
     * Algoritmo de Kadane: solución óptima O(n)
     * Mantiene la suma máxima encontrada hasta el momento y la suma actual.
     * 
     * @param arr Array de enteros
     * @return La suma máxima del subarray contiguo
     */
    public static int máximoSubarrayKadane(int[] arr) {
        if (arr.length == 0) return 0;
        
        int sumaMaxima = arr[0];
        int sumaActual = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            // Decidir si extender el subarray actual o comenzar uno nuevo
            sumaActual = Math.max(arr[i], sumaActual + arr[i]);
            // Actualizar la suma máxima encontrada
            sumaMaxima = Math.max(sumaMaxima, sumaActual);
        }
        
        return sumaMaxima;
    }
    
    /**
     * Versión con información completa: retorna la suma máxima y los índices
     */
    public static class Resultado {
        public int sumaMaxima;
        public int inicio;
        public int fin;
        
        public Resultado(int sumaMaxima, int inicio, int fin) {
            this.sumaMaxima = sumaMaxima;
            this.inicio = inicio;
            this.fin = fin;
        }
    }
    
    public static Resultado máximoSubarrayConIndices(int[] arr) {
        if (arr.length == 0) return new Resultado(0, -1, -1);
        
        int sumaMaxima = arr[0];
        int sumaActual = arr[0];
        int inicioActual = 0;
        int inicio = 0;
        int fin = 0;
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > sumaActual + arr[i]) {
                sumaActual = arr[i];
                inicioActual = i;
            } else {
                sumaActual = sumaActual + arr[i];
            }
            
            if (sumaActual > sumaMaxima) {
                sumaMaxima = sumaActual;
                inicio = inicioActual;
                fin = i;
            }
        }
        
        return new Resultado(sumaMaxima, inicio, fin);
    }
    
    /**
     * Versión Divide y Vencerás: O(n log n)
     * Divide el array en dos mitades y encuentra el máximo en:
     * 1. Mitad izquierda
     * 2. Mitad derecha
     * 3. Cruzando el punto medio
     */
    public static int máximoSubarrayDyV(int[] arr, int izquierda, int derecha) {
        // Caso base: un único elemento
        if (izquierda == derecha) {
            return arr[izquierda];
        }
        
        int medio = izquierda + (derecha - izquierda) / 2;
        
        // Máximo en mitad izquierda
        int maxIzquierda = máximoSubarrayDyV(arr, izquierda, medio);
        
        // Máximo en mitad derecha
        int maxDerecha = máximoSubarrayDyV(arr, medio + 1, derecha);
        
        // Máximo cruzando el punto medio
        int sumaIzquierda = 0;
        int maxCruzandoIzquierda = Integer.MIN_VALUE;
        for (int i = medio; i >= izquierda; i--) {
            sumaIzquierda += arr[i];
            maxCruzandoIzquierda = Math.max(maxCruzandoIzquierda, sumaIzquierda);
        }
        
        int sumaDerecha = 0;
        int maxCruzandoDerecha = Integer.MIN_VALUE;
        for (int i = medio + 1; i <= derecha; i++) {
            sumaDerecha += arr[i];
            maxCruzandoDerecha = Math.max(maxCruzandoDerecha, sumaDerecha);
        }
        
        int maxCruzando = maxCruzandoIzquierda + maxCruzandoDerecha;
        
        // Retornar el máximo de los tres
        return Math.max(maxCruzando, Math.max(maxIzquierda, maxDerecha));
    }
    
    public static void main(String[] args) {
        int[] arr1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] arr2 = {-5, -2, -8, -1, -4};
        int[] arr3 = {1, 2, 3, 4, 5};
        
        System.out.println("=== Máximo Subarray - Algoritmo de Kadane ===");
        System.out.println("Array 1: [-2, 1, -3, 4, -1, 2, 1, -5, 4]");
        System.out.println("Suma máxima: " + máximoSubarrayKadane(arr1));
        
        System.out.println("\nArray 2: [-5, -2, -8, -1, -4]");
        System.out.println("Suma máxima: " + máximoSubarrayKadane(arr2));
        
        System.out.println("\nArray 3: [1, 2, 3, 4, 5]");
        System.out.println("Suma máxima: " + máximoSubarrayKadane(arr3));
        
        // Con índices
        System.out.println("\n=== Máximo Subarray con Índices ===");
        Resultado res = máximoSubarrayConIndices(arr1);
        System.out.println("Array: [-2, 1, -3, 4, -1, 2, 1, -5, 4]");
        System.out.println("Suma máxima: " + res.sumaMaxima);
        System.out.println("Desde índice: " + res.inicio + " hasta " + res.fin);
        System.out.print("Subarray: [");
        for (int i = res.inicio; i <= res.fin; i++) {
            System.out.print(arr1[i] + (i < res.fin ? ", " : ""));
        }
        System.out.println("]");
        
        // Divide y Vencerás
        System.out.println("\n=== Máximo Subarray - Divide y Vencerás ===");
        System.out.println("Array: [-2, 1, -3, 4, -1, 2, 1, -5, 4]");
        System.out.println("Suma máxima: " + máximoSubarrayDyV(arr1, 0, arr1.length - 1));
    }
}
