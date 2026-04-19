# ☀️ Reto 02: Solar Metrics Aggregator

![Java](https://img.shields.io/badge/Java-Core-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Clean Code](https://img.shields.io/badge/Clean_Code-25A162?style=for-the-badge)

Este módulo forma parte de la fase "Fundamentos Core" del entrenamiento de maestría en Backend. El objetivo es procesar telemetría cruda proveniente de inversores solares fotovoltaicos, calculando la energía total generada, promedios y detectando anomalías o caídas de tensión.

A través de este reto, demostramos por qué la aritmética de punto flotante (`double` o `float`) es peligrosa e inaceptable en sistemas financieros o métricas críticas empresariales.

## 🧠 Conceptos Core Aplicados

* **Matemáticas de Alta Precisión (`BigDecimal`):** Uso de tipos de datos inmutables y exactos para evitar el problema de los decimales fantasma (floating-point arithmetic precision issues) inherentes a las arquitecturas de CPU.
* **Manejo Seguro de Escalas y Redondeo:** Implementación estricta de `RoundingMode` y definición de escalas en divisiones para prevenir `ArithmeticException` en números periódicos.
* **Control de Flujo Clásico:** Uso eficiente de arrays primitivos y bucles (`for`, `for-each`) para la iteración secuencial de series de tiempo.
* **Lógica de Comparación Segura:** Sustitución de operadores lógicos tradicionales (`<`, `>`, `==`) por el método `.compareTo()` necesario en objetos inmutables.

## 🏗️ Estructura del Proyecto

```text
src/
└── main/java
    ├── SolarMetricsProcessor.java   # Core lógico y cálculos con BigDecimal
    └── Main.java                    # Simulador de telemetría y prueba de vulnerabilidad del 'double'