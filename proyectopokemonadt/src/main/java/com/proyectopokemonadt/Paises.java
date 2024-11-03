package com.proyectopokemonadt;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "paises")
public class Paises {
    private List<Pais> paises;

    @XmlElement(name = "pais")
    public List<Pais> getPaises() {
            return paises;
       }
    public void setPaises(List<Pais>paises){
        this.paises = paises;
    } 
}
