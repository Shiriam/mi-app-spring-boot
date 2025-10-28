package com.isabellaapp.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.isabellaapp.demo.Calculadora;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;  // ← ¡IMPORTANTE! Agregar este import

@SpringBootTest
@AutoConfigureMockMvc
public class CalculadoraControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private Calculadora calculadora;
    
    @Test
    public void testSumaEndpoint() throws Exception {
        mockMvc.perform(get("/api/calculadora/suma")
                .param("a", "5")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operacion").value("suma"))
                .andExpect(jsonPath("$.resultado").value(8));
    }
    
    @Test
    public void testRestaEndpoint() throws Exception {
        mockMvc.perform(get("/api/calculadora/resta")
                .param("a", "10")
                .param("b", "4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").value(6));
    }
    
    @Test
    public void testCalculadoraInyectada() {
        assertNotNull(calculadora, "La calculadora debería estar inyectada por Spring");
    }
    
    @Test
    public void testMultiplicacionEndpoint() throws Exception {
        mockMvc.perform(get("/api/calculadora/multiplicacion")
                .param("a", "6")
                .param("b", "7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").value(42));
    }
    
    @Test
    public void testDivisionEndpoint() throws Exception {
        mockMvc.perform(get("/api/calculadora/division")
                .param("a", "15")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").value(5.0));
    }
    
    @Test
    public void testSaludoEndpoint() throws Exception {
        mockMvc.perform(get("/api/calculadora/saludo"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hola desde el servicio de Calculadora"));
    }
}