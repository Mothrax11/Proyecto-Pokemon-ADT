package com.proyectopokemonadt;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Exportar {
     private static String nombre;
     private static String nacionalidad;
     private static long puntos;
     private static LocalDate fechaCreacion;
    
    public Exportar(String nombre, String nacionalidad, long puntos, LocalDate fechaCreacion) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.puntos = puntos;
        this.fechaCreacion = fechaCreacion;
    }
    public void ejecutar() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = factory.newDocumentBuilder();
            DOMImplementation domImplementation = docBuilder.getDOMImplementation();
            Document document = domImplementation.createDocument(null, "carnet", null);
            crearXML(document);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void crearXML(Document doc){
        doc.setXmlVersion("1.0");
        long id = 1000;
        LocalDate ld = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        String dateString = ld.format(dateTimeFormatter);
        String dateStrinUsr = fechaCreacion.format(dateTimeFormatter);

        crearElemento("id", Long.toString(id), doc, doc.getDocumentElement());
        crearElemento("fechaexp", dateString, doc, doc.getDocumentElement());

        Element elementEntrenador = doc.createElement("entrenador");
        doc.getDocumentElement().appendChild(elementEntrenador);
        crearElemento("nombre", nombre, doc, elementEntrenador);
        crearElemento("nacionalidad", nacionalidad, doc, elementEntrenador);
        crearElemento("hoy", dateStrinUsr, doc, elementEntrenador);
        crearElemento("puntos", Long.toString(puntos), doc, elementEntrenador);
        
        //Element elementoTorneos = doc.createElement("torneos");
        //doc.getDocumentElement().appendChild(elementoTorneos);
        //Element elementoTorneo = doc.createElement("torneo");
        //doc.getDocumentElement().appendChild(elementoTorneo);
        //crearElemento("nombre", nacionalidad, doc, elementEntrenador);
        //crearElemento("region", nacionalidad, doc, elementEntrenador);

        Source source = new DOMSource(doc);
        Result result = new StreamResult(new File(nombre + ".xml"));

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            try {
                transformer.transform(source, result);
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        
    }
    
    private static void crearElemento(String dato, String valor, Document document, Element raiz) {
        Element element = document.createElement(dato);
        Text text = document.createTextNode(valor);
        raiz.appendChild(element);
        element.appendChild(text);
    }


    
    
}
