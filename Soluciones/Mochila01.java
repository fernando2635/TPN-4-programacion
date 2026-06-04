/**
 * Ejercicio 3: Problema de la Mochila Discreto (0/1)
 * Técnica: Programación Dinámica
 * 
 * Dado:
 * - n objetos con pesos w[i] y valores v[i]
 * - Capacidad máxima de la mochila W
 * 
 * Encontrar: combinación de objetos que maximiza el valor sin exceder la capacidad
 * Restricción: cada objeto se toma completo o no se toma (0/1)
 */

public class Mochila01 {
    
    /**
     * Estructura para almacenar información de un objeto
     */
    public static class Objeto {
        public int peso;
        public int valor;
        
        public Objeto(int peso, int valor) {
            this.peso = peso;
            this.valor = valor;
        }
    }
    
    /**
     * Resuelve el problema de la mochila 0/1 usando Programación Dinámica
     * 
     * @param objetos Array de objetos con peso y valor
     * @param capacidad Capacidad máxima de la mochila
     * @return Valor máximo que se puede obtener
     * 
     * Complejidad: O(n * W) donde n es el número de objetos
     */
    public static int mochilaDP(Objeto[] objetos, int capacidad) {
        int n = objetos.length;
        
        // dp[i][w] = valor máximo usando los primeros i objetos con capacidad w
        int[][] dp = new int[n + 1][capacidad + 1];
        
        // Llenar la tabla
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacidad; w++) {
                // No incluir el objeto i-1
                dp[i][w] = dp[i - 1][w];
                
                // Incluir el objeto i-1 si cabe
                if (objetos[i - 1].peso <= w) {
                    int valorConObjeto = dp[i - 1][w - objetos[i - 1].peso] + objetos[i - 1].valor;
                    dp[i][w] = Math.max(dp[i][w], valorConObjeto);
                }
            }
        }
        
        return dp[n][capacidad];
    }
    
    /**
     * Versión optimizada en espacio O(W)
     * Utiliza solo dos filas en lugar de una matriz
     */
    public static int mochilaDP_Optimizado(Objeto[] objetos, int capacidad) {
        int[] dp = new int[capacidad + 1];
        
        for (Objeto obj : objetos) {
            // Recorrer hacia atrás para evitar usar el mismo objeto dos veces
            for (int w = capacidad; w >= obj.peso; w--) {
                dp[w] = Math.max(dp[w], dp[w - obj.peso] + obj.valor);
            }
        }
        
        return dp[capacidad];
    }
    
    /**
     * Resuelve el problema y retorna qué objetos se seleccionan
     */
    public static class ResultadoMochila {
        public int valorMaximo;
        public boolean[] seleccionados;
        
        public ResultadoMochila(int valorMaximo, boolean[] seleccionados) {
            this.valorMaximo = valorMaximo;
            this.seleccionados = seleccionados;
        }
    }
    
    public static ResultadoMochila mochilaConSelección(Objeto[] objetos, int capacidad) {
        int n = objetos.length;
        int[][] dp = new int[n + 1][capacidad + 1];
        
        // Llenar la tabla
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacidad; w++) {
                dp[i][w] = dp[i - 1][w];
                
                if (objetos[i - 1].peso <= w) {
                    int valorConObjeto = dp[i - 1][w - objetos[i - 1].peso] + objetos[i - 1].valor;
                    dp[i][w] = Math.max(dp[i][w], valorConObjeto);
                }
            }
        }
        
        // Rastrear hacia atrás para encontrar qué objetos se seleccionan
        boolean[] seleccionados = new boolean[n];
        int w = capacidad;
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                seleccionados[i - 1] = true;
                w -= objetos[i - 1].peso;
            }
        }
        
        return new ResultadoMochila(dp[n][capacidad], seleccionados);
    }
    
    public static void main(String[] args) {
        // Prueba 1
        System.out.println("=== Problema de la Mochila 0/1 - Prueba 1 ===");
        Objeto[] objetos1 = {
            new Objeto(2, 3),    // peso=2, valor=3
            new Objeto(3, 4),    // peso=3, valor=4
            new Objeto(4, 5),    // peso=4, valor=5
            new Objeto(5, 6)     // peso=5, valor=6
        };
        int capacidad1 = 8;
        
        System.out.println("Objetos (peso, valor):");
        for (int i = 0; i < objetos1.length; i++) {
            System.out.println("  Objeto " + i + ": peso=" + objetos1[i].peso + ", valor=" + objetos1[i].valor);
        }
        System.out.println("Capacidad de la mochila: " + capacidad1);
        System.out.println("Valor máximo (DP estándar): " + mochilaDP(objetos1, capacidad1));
        System.out.println("Valor máximo (DP optimizado): " + mochilaDP_Optimizado(objetos1, capacidad1));
        
        // Con selección
        ResultadoMochila resultado = mochilaConSelección(objetos1, capacidad1);
        System.out.println("Valor máximo (con selección): " + resultado.valorMaximo);
        System.out.print("Objetos seleccionados: ");
        for (int i = 0; i < resultado.seleccionados.length; i++) {
            if (resultado.seleccionados[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        
        // Prueba 2
        System.out.println("\n=== Problema de la Mochila 0/1 - Prueba 2 ===");
        Objeto[] objetos2 = {
            new Objeto(10, 60),
            new Objeto(20, 100),
            new Objeto(30, 120)
        };
        int capacidad2 = 50;
        
        System.out.println("Objetos (peso, valor):");
        for (int i = 0; i < objetos2.length; i++) {
            System.out.println("  Objeto " + i + ": peso=" + objetos2[i].peso + ", valor=" + objetos2[i].valor);
        }
        System.out.println("Capacidad de la mochila: " + capacidad2);
        System.out.println("Valor máximo: " + mochilaDP(objetos2, capacidad2));
        
        resultado = mochilaConSelección(objetos2, capacidad2);
        System.out.print("Objetos seleccionados: ");
        int pesoTotal = 0;
        for (int i = 0; i < resultado.seleccionados.length; i++) {
            if (resultado.seleccionados[i]) {
                System.out.print(i + " ");
                pesoTotal += objetos2[i].peso;
            }
        }
        System.out.println();
        System.out.println("Peso total: " + pesoTotal);
        System.out.println("Valor total: " + resultado.valorMaximo);
    }
}
