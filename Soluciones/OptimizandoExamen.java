/**
 * Ejercicio 5: Optimizando Examen
 * Técnica: Programación Dinámica
 * 
 * Problema: Maximizar la importancia total de asignaturas aprobadas
 * con un número limitado de días de estudio.
 * 
 * Para cada asignatura:
 * - Importancia (valor/créditos)
 * - Mínimo de días para aprobar
 * - Máximo de días donde se sigue beneficiando
 * - La importancia se incrementa linealmente entre min y max
 */

public class OptimizandoExamen {
    
    /**
     * Estructura para representar una asignatura
     */
    public static class Asignatura {
        public int importancia;      // Importancia base (valor máximo)
        public int diasMínimos;      // Días mínimos para aprobar
        public int diasMáximos;      // Días máximos útiles
        public int id;
        
        public Asignatura(int id, int importancia, int diasMínimos, int diasMáximos) {
            this.id = id;
            this.importancia = importancia;
            this.diasMínimos = diasMínimos;
            this.diasMáximos = diasMáximos;
        }
        
        @Override
        public String toString() {
            return "Asignatura " + id + " (imp=" + importancia + ", min=" + diasMínimos + ", max=" + diasMáximos + ")";
        }
    }
    
    /**
     * Calcula la importancia obtenida estudiando X días para una asignatura
     * Si X < diasMínimos: retorna 0 (no aprobada)
     * Si X >= diasMáximos: retorna importancia (beneficio máximo)
     * En medio: retorna importancia * (X - min) / (max - min)
     */
    public static double calcularImportancia(Asignatura asignatura, int días) {
        if (días < asignatura.diasMínimos) {
            return 0;
        }
        if (días >= asignatura.diasMáximos) {
            return asignatura.importancia;
        }
        
        // Interpolación lineal
        double progreso = (double) (días - asignatura.diasMínimos) / 
                         (asignatura.diasMáximos - asignatura.diasMínimos);
        return asignatura.importancia * progreso;
    }
    
    /**
     * Resuelve el problema usando Programación Dinámica
     * Usa una aproximación discreta considerando días enteros
     * 
     * @param asignaturas Array de asignaturas
     * @param díasDisponibles Total de días para estudiar
     * @return Importancia máxima obtenible
     */
    public static double optimizarExamen(Asignatura[] asignaturas, int díasDisponibles) {
        int n = asignaturas.length;
        
        // dp[i][d] = máxima importancia usando asignaturas 0..i-1 con d días
        double[][] dp = new double[n + 1][díasDisponibles + 1];
        
        // Llenar la tabla
        for (int i = 1; i <= n; i++) {
            Asignatura actual = asignaturas[i - 1];
            
            for (int d = 0; d <= díasDisponibles; d++) {
                // Opción 1: No estudiar esta asignatura
                dp[i][d] = dp[i - 1][d];
                
                // Opción 2: Estudiar X días esta asignatura (desde 0 hasta d)
                for (int x = 0; x <= d; x++) {
                    double importancia = calcularImportancia(actual, x);
                    double total = dp[i - 1][d - x] + importancia;
                    dp[i][d] = Math.max(dp[i][d], total);
                }
            }
        }
        
        return dp[n][díasDisponibles];
    }
    
    /**
     * Versión con rastreo para saber qué estudiar cada asignatura
     */
    public static class PlanEstudio {
        public double importanciaTotal;
        public int[] díasPorAsignatura;
        
        public PlanEstudio(double importanciaTotal, int[] díasPorAsignatura) {
            this.importanciaTotal = importanciaTotal;
            this.díasPorAsignatura = díasPorAsignatura;
        }
    }
    
    public static PlanEstudio optimizarExamenConPlan(Asignatura[] asignaturas, int díasDisponibles) {
        int n = asignaturas.length;
        double[][] dp = new double[n + 1][díasDisponibles + 1];
        int[][] elección = new int[n + 1][díasDisponibles + 1];
        
        // Llenar la tabla
        for (int i = 1; i <= n; i++) {
            Asignatura actual = asignaturas[i - 1];
            
            for (int d = 0; d <= díasDisponibles; d++) {
                dp[i][d] = dp[i - 1][d];
                elección[i][d] = 0; // No estudiar
                
                for (int x = 1; x <= d; x++) {
                    double importancia = calcularImportancia(actual, x);
                    double total = dp[i - 1][d - x] + importancia;
                    
                    if (total > dp[i][d]) {
                        dp[i][d] = total;
                        elección[i][d] = x;
                    }
                }
            }
        }
        
        // Rastrear hacia atrás
        int[] díasPorAsignatura = new int[n];
        int diasRestantes = díasDisponibles;
        
        for (int i = n; i > 0; i--) {
            int días = elección[i][diasRestantes];
            díasPorAsignatura[i - 1] = días;
            diasRestantes -= días;
        }
        
        return new PlanEstudio(dp[n][díasDisponibles], díasPorAsignatura);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Optimizando Examen ===\n");
        
        // Caso de prueba 1
        System.out.println("=== Prueba 1 ===");
        Asignatura[] asignaturas1 = {
            new Asignatura(1, 100, 2, 5),  // Importancia 100, min 2 días, max 5 días
            new Asignatura(2, 80, 1, 4),   // Importancia 80, min 1 día, max 4 días
            new Asignatura(3, 60, 1, 3)    // Importancia 60, min 1 día, max 3 días
        };
        int días1 = 10;
        
        System.out.println("Asignaturas:");
        for (Asignatura a : asignaturas1) {
            System.out.println("  " + a);
        }
        System.out.println("Días disponibles: " + días1);
        
        double resultado = optimizarExamen(asignaturas1, días1);
        System.out.println("Importancia máxima: " + Math.round(resultado * 100.0) / 100.0);
        
        PlanEstudio plan = optimizarExamenConPlan(asignaturas1, días1);
        System.out.println("Plan óptimo:");
        int totalDías = 0;
        for (int i = 0; i < asignaturas1.length; i++) {
            int dias = plan.díasPorAsignatura[i];
            totalDías += dias;
            double imp = calcularImportancia(asignaturas1[i], dias);
            System.out.println("  Asignatura " + asignaturas1[i].id + ": " + dias + " días -> Importancia: " + 
                             Math.round(imp * 100.0) / 100.0);
        }
        System.out.println("Total de días: " + totalDías);
        System.out.println("Importancia total: " + Math.round(plan.importanciaTotal * 100.0) / 100.0);
        
        // Caso de prueba 2
        System.out.println("\n=== Prueba 2 ===");
        Asignatura[] asignaturas2 = {
            new Asignatura(1, 90, 3, 6),
            new Asignatura(2, 70, 2, 5),
            new Asignatura(3, 50, 1, 4),
            new Asignatura(4, 60, 2, 4)
        };
        int días2 = 12;
        
        System.out.println("Asignaturas:");
        for (Asignatura a : asignaturas2) {
            System.out.println("  " + a);
        }
        System.out.println("Días disponibles: " + días2);
        
        resultado = optimizarExamen(asignaturas2, días2);
        System.out.println("Importancia máxima: " + Math.round(resultado * 100.0) / 100.0);
        
        plan = optimizarExamenConPlan(asignaturas2, días2);
        System.out.println("Plan óptimo:");
        totalDías = 0;
        for (int i = 0; i < asignaturas2.length; i++) {
            int dias = plan.díasPorAsignatura[i];
            totalDías += dias;
            double imp = calcularImportancia(asignaturas2[i], dias);
            if (dias > 0) {
                System.out.println("  Asignatura " + asignaturas2[i].id + ": " + dias + " días -> Importancia: " + 
                                 Math.round(imp * 100.0) / 100.0);
            }
        }
        System.out.println("Total de días: " + totalDías);
        System.out.println("Importancia total: " + Math.round(plan.importanciaTotal * 100.0) / 100.0);
    }
}
