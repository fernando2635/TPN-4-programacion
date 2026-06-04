/**
 * Ejercicio 4: Instituto Con Pocas Contraseñas (ICPC)
 * Técnica: Secuencia Fibonacci con Programación Dinámica
 * 
 * El abuelo Laino genera su contraseña así:
 * - Comienza con 46 (año de nacimiento)
 * - Los siguientes dígitos se obtienen como suma de dos dígitos anteriores
 * 
 * Ejemplo: 46 -> 4+6=10 -> 6+1+0=7 -> ...
 * Más precisamente: 46123581321...
 * 
 * Dado X, Y, Z, W (posiciones), retornar los 4 dígitos en esas posiciones.
 */

public class ICPC {
    
    /**
     * Genera la secuencia de contraseña del abuelo Laino
     * Secuencia: 4, 6, 1, 0, 1, 1, 2, 3, 5, 8, 1, 3, 2, 1...
     * 
     * @param n Número de dígitos a generar
     * @return Array con los primeros n dígitos de la secuencia
     */
    public static int[] generarSecuencia(int n) {
        int[] secuencia = new int[n];
        
        // Los primeros dos dígitos son 4 y 6
        if (n > 0) secuencia[0] = 4;
        if (n > 1) secuencia[1] = 6;
        
        // El resto se genera como suma de los dos anteriores (módulo 10 para mantener un dígito)
        for (int i = 2; i < n; i++) {
            secuencia[i] = (secuencia[i - 1] + secuencia[i - 2]) % 10;
        }
        
        return secuencia;
    }
    
    /**
     * Obtiene el dígito en la posición especificada
     * Las posiciones empiezan desde 1
     * 
     * @param posicion Posición del dígito (1-indexado)
     * @return El dígito en esa posición
     */
    public static int obtenerDígito(int posicion) {
        if (posicion <= 0) return -1;
        
        // Generar suficientes dígitos
        int[] secuencia = generarSecuencia(posicion + 100);
        return secuencia[posicion - 1];
    }
    
    /**
     * Obtiene 4 dígitos en las posiciones especificadas
     * 
     * @param x Primera posición
     * @param y Segunda posición
     * @param z Tercera posición
     * @param w Cuarta posición
     * @return Número de 4 dígitos formado por los dígitos en esas posiciones
     */
    public static int obtener4Dígitos(int x, int y, int z, int w) {
        int[] secuencia = generarSecuencia(Math.max(Math.max(x, y), Math.max(z, w)) + 100);
        
        int d1 = secuencia[x - 1];
        int d2 = secuencia[y - 1];
        int d3 = secuencia[z - 1];
        int d4 = secuencia[w - 1];
        
        return d1 * 1000 + d2 * 100 + d3 * 10 + d4;
    }
    
    /**
     * Versión con caché para calcular dígitos grandes sin generar toda la secuencia
     */
    private static int[] caché = null;
    private static final int TAMAÑO_CACHÉ = 10000;
    
    public static void inicializarCaché() {
        caché = generarSecuencia(TAMAÑO_CACHÉ);
    }
    
    public static int obtenerDígito_ConCaché(int posicion) {
        if (caché == null) inicializarCaché();
        
        if (posicion <= TAMAÑO_CACHÉ) {
            return caché[posicion - 1];
        }
        
        // Para posiciones muy grandes, continuar desde el caché
        int[] secuencia = new int[posicion + 1];
        System.arraycopy(caché, 0, secuencia, 0, TAMAÑO_CACHÉ);
        
        for (int i = TAMAÑO_CACHÉ; i < posicion; i++) {
            secuencia[i] = (secuencia[i - 1] + secuencia[i - 2]) % 10;
        }
        
        return secuencia[posicion - 1];
    }
    
    public static void main(String[] args) {
        System.out.println("=== Instituto Con Pocas Contraseñas (ICPC) ===");
        
        // Mostrar la secuencia
        System.out.println("\nSecuencia de la contraseña (primeros 40 dígitos):");
        int[] secuencia = generarSecuencia(40);
        for (int i = 0; i < secuencia.length; i++) {
            System.out.print(secuencia[i]);
        }
        System.out.println();
        
        // Mostrar posiciones
        System.out.println("\nPosiciones:");
        for (int i = 0; i < secuencia.length; i++) {
            System.out.print((i + 1) + " ");
            if ((i + 1) % 10 == 0) System.out.println();
        }
        System.out.println();
        
        // Prueba 1: Obtener dígitos específicos
        System.out.println("\n=== Prueba 1 ===");
        int x = 1, y = 2, z = 3, w = 4;
        int resultado = obtener4Dígitos(x, y, z, w);
        System.out.println("Posiciones: " + x + ", " + y + ", " + z + ", " + w);
        System.out.println("Dígitos: " + resultado);
        System.out.println("Dígitos individuales: " + 
            obtenerDígito(x) + " " + obtenerDígito(y) + " " + 
            obtenerDígito(z) + " " + obtenerDígito(w));
        
        // Prueba 2
        System.out.println("\n=== Prueba 2 ===");
        x = 5; y = 10; z = 15; w = 20;
        resultado = obtener4Dígitos(x, y, z, w);
        System.out.println("Posiciones: " + x + ", " + y + ", " + z + ", " + w);
        System.out.println("Dígitos: " + resultado);
        System.out.println("Dígitos individuales: " + 
            obtenerDígito(x) + " " + obtenerDígito(y) + " " + 
            obtenerDígito(z) + " " + obtenerDígito(w));
        
        // Prueba 3: Posiciones grandes
        System.out.println("\n=== Prueba 3 (Posiciones grandes) ===");
        x = 100; y = 200; z = 300; w = 400;
        resultado = obtener4Dígitos(x, y, z, w);
        System.out.println("Posiciones: " + x + ", " + y + ", " + z + ", " + w);
        System.out.println("Dígitos: " + resultado);
    }
}
