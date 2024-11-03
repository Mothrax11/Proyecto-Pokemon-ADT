package com.proyectopokemonadt;

import org.w3c.dom.*;
import com.proyectopokemonadt.clasesBasicas.Torneo;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Exportar {
     public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public long getPuntos() {
        return puntos;
    }

    public void setPuntos(long puntos) {
        this.puntos = puntos;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public ArrayList<Torneo> getTorneos() {
        return torneos;
    }

    public void setTorneos(ArrayList<Torneo> torneos) {
        this.torneos = torneos;
    }

    public long getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(long idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    private  String nombre;
     private  String nacionalidad;
     private  long puntos;
     private  LocalDate fechaCreacion;
     private  ArrayList<Torneo> torneos = new ArrayList<>();
     private  long idEntrenador;
    
    public Exportar(String nombre, String nacionalidad, long puntos, LocalDate fechaCreacion, long idEntrenador, ArrayList<Torneo> torneos) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.puntos = puntos;
        this.fechaCreacion = fechaCreacion;
        this.torneos = torneos;   
        this.idEntrenador = idEntrenador; 
    }

    public void ejecutar() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = factory.newDocumentBuilder();
            DOMImplementation domImplementation = docBuilder.getDOMImplementation();
            Document document = domImplementation.createDocument(null, "carnet", null);
            crearXML(document);
        } catch (ParserConfigurationException e){
            e.printStackTrace();
        }
    }

    public  void crearXML(Document doc){
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
        
        Element elementoTorneos = doc.createElement("torneos");
        doc.getDocumentElement().appendChild(elementoTorneos);
        for (int i = 0; i < torneos.size(); i++){
            if(torneos.get(i).getIdResponsable() == idEntrenador){

                Element elementoTorneo = doc.createElement("torneo");
                elementoTorneos.appendChild(elementoTorneo);
                crearElemento("nombre", torneos.get(i).getNombre(), doc, elementoTorneo);

                String regionTorneo = Character.toString(torneos.get(i).getCodRegion());
                crearElemento("region", regionTorneo, doc, elementoTorneo);


            }
        }

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
