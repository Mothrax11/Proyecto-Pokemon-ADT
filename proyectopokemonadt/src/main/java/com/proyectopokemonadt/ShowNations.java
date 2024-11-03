package com.proyectopokemonadt;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class ShowNations {
    public static String show () {
        try {
            JAXBContext jaxbContent = JAXBContext.newInstance(Paises.class);
            Unmarshaller um = jaxbContent.createUnmarshaller();
            
            Paises paises = (Paises) um.unmarshal(new File("proyectopokemonadt\\src\\main\\java\\com\\proyectopokemonadt\\archviosComplementarios\\paises.xml"));
            StringBuilder s = new StringBuilder();

            for (Pais pais : paises.getPaises()) {
                s.append(pais.getNombre()).append("\n");
            }

            return s.toString();
        } catch (Exception e) {
           System.out.println(e.getMessage()); 
        }
        return null;
    }
}
