package com.icai.practicas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class TestDNI {
    
    @Test
    public void validarDNItesting(){
        
        //DNIS correctos
        DNI dni = new DNI("62290678J");
        assertEquals(true, dni.validar()); 
        dni = new DNI("41132893N");
        assertEquals(true, dni.validar());
        dni = new DNI("548960758A");
        assertEquals(true, dni.validar());

        //DNIs incorrectos
        dni = new DNI("1868247906");
        assertEquals(false, dni.validar());
        dni = new DNI("729863@@");
        assertEquals(false, dni.validar());
        dni = new DNI("890");
        assertEquals(false, dni.validar());
        dni = new DNI("56789");
        assertEquals(false, dni.validar());
        dni = new DNI("EEEE78W");
        assertEquals(false, dni.validar());

        



        





        
    }
}
