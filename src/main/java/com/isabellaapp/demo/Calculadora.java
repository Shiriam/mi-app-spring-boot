package com.isabellaapp.demo;

import org.springframework.stereotype.Service;

@Service
public class Calculadora {
    
    public int suma(int a, int b) {
        return a + b;
    }
    
    public int resta(int a, int b) {
        return a - b;
    }
    
    public int multiplicacion(int a, int b) {
        return a * b;
    }
    
    public double division(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("División por cero");
        }
        return (double) a / b;
    }
    
    // Método adicional que podrías usar en una aplicación real
    public String saludar() {
        return "Hola desde el servicio de Calculadora";
    }
}
