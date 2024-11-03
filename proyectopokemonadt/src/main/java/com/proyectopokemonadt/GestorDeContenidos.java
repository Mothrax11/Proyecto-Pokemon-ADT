package com.proyectopokemonadt;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class GestorDeContenidos extends DefaultHandler {
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String caracteres = new String(ch,start,length);
        ShowNations.listaPaises.add(caracteres);
        System.out.print(caracteres);
    }
}