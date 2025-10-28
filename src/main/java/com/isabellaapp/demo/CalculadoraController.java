package com.isabellaapp.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.isabellaapp.demo.Calculadora;

@RestController
@RequestMapping("/api/calculadora")
public class CalculadoraController {
    
    @Autowired
    private Calculadora calculadora;
    
    // SUMA: http://localhost:8080/api/calculadora/suma?a=5&b=3
    @GetMapping("/suma")
    public Resultado suma(@RequestParam int a, @RequestParam int b) {
        int resultado = calculadora.suma(a, b);
        return new Resultado("suma", a, b, resultado);
    }
    
    // RESTA: http://localhost:8080/api/calculadora/resta?a=10&b=4
    @GetMapping("/resta")
    public Resultado resta(@RequestParam int a, @RequestParam int b) {
        int resultado = calculadora.resta(a, b);
        return new Resultado("resta", a, b, resultado);
    }
    
    // MULTIPLICACIÓN: http://localhost:8080/api/calculadora/multiplicacion?a=6&b=7
    @GetMapping("/multiplicacion")
    public Resultado multiplicacion(@RequestParam int a, @RequestParam int b) {
        int resultado = calculadora.multiplicacion(a, b);
        return new Resultado("multiplicacion", a, b, resultado);
    }
    
    // DIVISIÓN: http://localhost:8080/api/calculadora/division?a=15&b=3
    @GetMapping("/division")
    public Object division(@RequestParam int a, @RequestParam int b) {
        try {
            double resultado = calculadora.division(a, b);
            return new ResultadoDecimal("division", a, b, resultado);
        } catch (ArithmeticException e) {
            return new ErrorResponse("Error en división", e.getMessage());
        }
    }
    
    // SALUDO: http://localhost:8080/api/calculadora/saludo
    @GetMapping("/saludo")
    public String saludo() {
        return calculadora.saludar();
    }
    
    // CLASE PARA RESULTADOS ENTEROS
    public static class Resultado {
        private String operacion;
        private int a;
        private int b;
        private int resultado;
        
        public Resultado(String operacion, int a, int b, int resultado) {
            this.operacion = operacion;
            this.a = a;
            this.b = b;
            this.resultado = resultado;
        }
        
        // Getters (necesarios para que Spring convierta a JSON)
        public String getOperacion() { return operacion; }
        public int getA() { return a; }
        public int getB() { return b; }
        public int getResultado() { return resultado; }
    }
    
    // CLASE PARA RESULTADOS DECIMALES (división)
    public static class ResultadoDecimal {
        private String operacion;
        private int a;
        private int b;
        private double resultado;
        
        public ResultadoDecimal(String operacion, int a, int b, double resultado) {
            this.operacion = operacion;
            this.a = a;
            this.b = b;
            this.resultado = resultado;
        }
        
        // Getters
        public String getOperacion() { return operacion; }
        public int getA() { return a; }
        public int getB() { return b; }
        public double getResultado() { return resultado; }
    }
    
    // CLASE PARA MANEJAR ERRORES
    public static class ErrorResponse {
        private String error;
        private String mensaje;
        
        public ErrorResponse(String error, String mensaje) {
            this.error = error;
            this.mensaje = mensaje;
        }
        
        // Getters
        public String getError() { return error; }
        public String getMensaje() { return mensaje; }
    }
}
