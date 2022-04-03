package com.icai.practicas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class TestTLFNO {
    
    @Test
    public void validarTELFtesting(){
        
        //Telefonos correctos
        Telefono tlf = new Telefono("617836829");
        assertEquals(true, tlf.validar()); 
        tlf = new Telefono("917367268");
        assertEquals(true, tlf.validar());
        
        //Telefonos incorrectos

        tlf = new Telefono("2");
        assertEquals(false, tlf.validar());
        tlf = new Telefono("hola");
        assertEquals(false, tlf.validar());
        tlf = new Telefono("h28b19b");
        assertEquals(false, tlf.validar());
        tlf = new Telefono("-189-2"); 
        assertEquals(false, tlf.validar());



        

        

    }
}