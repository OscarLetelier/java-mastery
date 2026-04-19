# Reto 01: Data Processing Engine

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)

Este es el primer módulo del entrenamiento de maestría en Backend. El objetivo de este proyecto es construir un motor de análisis de datos inmutable y altamente concurrente, capaz de procesar masivamente registros de soporte técnico sin agotar los recursos del sistema operativo.

A través de este reto, abandonamos las prácticas de Java legado (clases anémicas, getters/setters, OS Threads) para abrazar las características modernas del lenguaje.

## Conceptos Avanzados Aplicados

* **Data-Oriented Programming (DOP):** Uso estricto de `Records` para garantizar la inmutabilidad de los datos de entrada (`Ticket`) y salida (`AnalyzedTicket`).
* **Pattern Matching:** Sustitución de complejas cadenas `if-else` por *Switch Expressions* con *Guard Clauses* (`when`) para una clasificación de datos segura y expresiva, sin efectos secundarios (Side-Effects).
* **Concurrencia Moderna (Proyecto Loom):** Implementación de **Virtual Threads** (`Executors.newVirtualThreadPerTaskExecutor()`) para procesar cientos de miles de registros en paralelo con un consumo de memoria casi nulo.
* **Stream API:** Recolección y filtrado fluido de resultados asíncronos (`CompletableFuture`).
* **Testing Automatizado:** Cobertura de la lógica de negocio core utilizando JUnit 5.

## Estructura del Proyecto

```text
src/
├── main/java/
│   ├── Ticket.java                 # Record inmutable de entrada
│   ├── AnalyzedTicket.java         # Record inmutable de salida
│   ├── TicketAnalyzerEngine.java   # Core de lógica de negocio y concurrencia
│   └── Main.java                   # Simulador de inyección masiva (100k+ registros)
└── test/java/com/tuusuario/
    └── TicketAnalyzerEngineTest.java # Pruebas unitarias de las reglas de clasificación