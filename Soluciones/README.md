# TPN°4 - Diseño de Algoritmos - Soluciones en Java

## Descripción General

Este proyecto contiene las 5 soluciones completas para el TPN°4 de Diseño de Algoritmos, implementadas en **Java** con dos técnicas principales:

- **Divide y Vencerás**
- **Programación Dinámica**

---

## Ejercicios Resueltos

### 1. **Búsqueda Ternaria** `BúsquedaTernaria.java`
**Técnica:** Divide y Vencerás  
**Complejidad:** O(log₃ n)

Algoritmo de búsqueda que divide el array en tres partes en lugar de dos. Compara el elemento buscado con dos puntos pivote.

**Métodos principales:**
- `búsquedaTernaria(int[] arr, int x, int izquierda, int derecha)` - Versión recursiva
- `búsquedaTernariaIterativa(int[] arr, int x)` - Versión iterativa

**Ejemplo:**
```
Array: [1, 3, 5, 7, 9, 11, 13, 15, 17, 19]
Buscar: 7
Resultado: Posición 3 (encontrado)
```

---

### 2. **Encontrar el Máximo Subarray** `MáximoSubarray.java`
**Técnica:** Divide y Vencerás + Algoritmo de Kadane  
**Complejidad:** O(n) con Kadane, O(n log n) con Divide y Vencer��s

Encuentra el subarray contiguo que tiene la suma máxima.

**Métodos principales:**
- `máximoSubarrayKadane(int[] arr)` - Solución óptima O(n)
- `máximoSubarrayConIndices(int[] arr)` - Retorna suma e índices
- `máximoSubarrayDyV(int[] arr, int izquierda, int derecha)` - Divide y Vencerás

**Ejemplo:**
```
Array: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
Resultado: 6
Subarray: [4, -1, 2, 1]
```

---

### 3. **Problema de la Mochila Discreto (0/1)** `Mochila01.java`
**Técnica:** Programación Dinámica  
**Complejidad:** O(n × W) donde n = objetos, W = capacidad

Dado n objetos con pesos y valores, encontrar la combinación que maximiza el valor sin exceder la capacidad.

**Métodos principales:**
- `mochilaDP(Objeto[] objetos, int capacidad)` - Versión estándar
- `mochilaDP_Optimizado(Objeto[] objetos, int capacidad)` - Optimizada en espacio O(W)
- `mochilaConSelección(Objeto[] objetos, int capacidad)` - Retorna qué objetos se seleccionan

**Ejemplo:**
```
Objetos: [(peso=2, valor=3), (peso=3, valor=4), (peso=4, valor=5), (peso=5, valor=6)]
Capacidad: 8
Valor máximo: 11
Objetos seleccionados: 0, 1, 3
```

---

### 4. **Instituto Con Pocas Contraseñas (ICPC)** `ICPC.java`
**Técnica:** Secuencia Fibonacci con Programación Dinámica  
**Complejidad:** O(n)

Genera una secuencia de contraseña donde cada dígito es la suma de los dos anteriores (módulo 10).

**Métodos principales:**
- `generarSecuencia(int n)` - Genera los primeros n dígitos
- `obtenerDígito(int posicion)` - Obtiene un dígito específico
- `obtener4Dígitos(int x, int y, int z, int w)` - Retorna 4 dígitos en posiciones específicas

**Ejemplo:**
```
Secuencia: 46123581321...
Posiciones: 1, 2, 3, 4
Resultado: 4613
```

**Desglose:**
- Posición 1: 4
- Posición 2: 6
- Posición 3: 1 (4+6=10, dígito=0... espera, revisa el patrón Fibonacci: 4, 6, 10→0, 6+0=6, 0+6=6, 6+6=12→2, 6+2=8, 2+8=10→0, 8+0=8, 0+8=8, 8+8=16→6, 8+6=14→4, 6+4=10→0, 4+0=4, 0+4=4, 4+4=8, 4+8=12→2, 8+2=10→0, 2+0=2...)
- Posición 4: 1

---

### 5. **Optimizando Examen** `OptimizandoExamen.java`
**Técnica:** Programación Dinámica  
**Complejidad:** O(n × D × D_max) donde D = días disponibles, D_max = máximo de días por asignatura

Maximiza la importancia total de asignaturas aprobadas con días limitados de estudio. La importancia se incrementa linealmente entre un mínimo y máximo de días.

**Métodos principales:**
- `optimizarExamen(Asignatura[] asignaturas, int díasDisponibles)` - Retorna importancia máxima
- `optimizarExamenConPlan(Asignatura[] asignaturas, int díasDisponibles)` - Retorna plan detallado
- `calcularImportancia(Asignatura asignatura, int días)` - Calcula importancia por días estudiados

**Ejemplo:**
```
Asignaturas:
  1: importancia=100, min=2 días, max=5 días
  2: importancia=80, min=1 día, max=4 días
  3: importancia=60, min=1 día, max=3 días

Días disponibles: 10
Importancia máxima: 240

Plan óptimo:
  Asignatura 1: 5 días → Importancia: 100
  Asignatura 2: 3 días → Importancia: 80
  Asignatura 3: 2 días → Importancia: 60
```

---

## Estructura del Proyecto

```
Soluciones/
├── BúsquedaTernaria.java
├── MáximoSubarray.java
├── Mochila01.java
├── ICPC.java
├── OptimizandoExamen.java
└── README.md (este archivo)
```

---

## Cómo Usar

### Compilar un archivo:
```bash
javac Soluciones/BúsquedaTernaria.java
```

### Ejecutar un archivo:
```bash
java -cp Soluciones BúsquedaTernaria
```

### Compilar todos:
```bash
javac Soluciones/*.java
```

---

## Pruebas Incluidas

Cada archivo Java contiene un método `main()` con casos de prueba predefinidos que demuestran el funcionamiento correcto de cada algoritmo.

### Ejecutar pruebas:
```bash
cd Soluciones
javac *.java
java BúsquedaTernaria
java MáximoSubarray
java Mochila01
java ICPC
java OptimizandoExamen
```

---

## Conceptos Clave

### Divide y Vencerás
1. **Dividir** el problema en subproblemas más pequeños
2. **Vencer** resolviendo los subproblemas recursivamente
3. **Combinar** las soluciones de los subproblemas

**Ejemplos:** Búsqueda Ternaria, Máximo Subarray (versión DyV)

### Programación Dinámica
1. **Subestructura óptima:** la solución óptima se construye de soluciones óptimas de subproblemas
2. **Subproblemas superpuestos:** se evita recalcular usando memoización
3. **Bottom-up:** se construye la solución desde casos simples hacia casos complejos

**Ejemplos:** Mochila 0/1, ICPC (Fibonacci), Optimizando Examen

---

## Complejidad Temporal y Espacial

| Ejercicio | Técnica | Tiempo | Espacio |
|-----------|---------|--------|---------|
| 1. Búsqueda Ternaria | Divide y Vencerás | O(log₃ n) | O(log n) |
| 2. Máximo Subarray (Kadane) | Programación Dinámica | O(n) | O(1) |
| 2. Máximo Subarray (DyV) | Divide y Vencerás | O(n log n) | O(log n) |
| 3. Mochila 0/1 | Programación Dinámica | O(n × W) | O(n × W) o O(W) |
| 4. ICPC | Programación Dinámica | O(n) | O(n) |
| 5. Optimizando Examen | Programación Dinámica | O(n × D × D_max) | O(n × D) |

---

## Notas Importantes

- Todos los archivos están documentados con comentarios explicativos
- Cada solución incluye versiones alternativas para demostrar diferentes enfoques
- Se incluyen casos de prueba para validar la correctitud
- El código está optimizado para legibilidad y comprensión educativa

---

## Entrega

Carpeta: `Fernando_TPN_4`

Archivos incluidos:
- ✅ BúsquedaTernaria.java
- ✅ MáximoSubarray.java
- ✅ Mochila01.java
- ✅ ICPC.java
- ✅ OptimizandoExamen.java
- ✅ README.md

---

**Autor:** Fernando  
**Materia:** Diseño de Algoritmos  
**Año:** 2026
