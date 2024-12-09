package com.proyectopokemonadt.complementarias;

import org.w3c.dom.*;

import com.proyectopokemonadt.ENTIDADES.Torneo;
import com.proyectopokemonadt.ENTIDADES.Combate;

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
import java.util.List;

public class Exportar {

    private String nombre;
    private String nacionalidad;
    private long puntos;
    private LocalDate fechaCreacion;
    private List<Torneo> torneos = new ArrayList<>();
    private long idEntrenador;
    private EntrenadorTemporal entrenador;

    public Exportar(EntrenadorTemporal entrenador) {
        this.entrenador = entrenador;
        this.nombre = entrenador.getNombre();
        this.idEntrenador = entrenador.getId();
        this.nacionalidad = entrenador.getNacionalidad();
        this.fechaCreacion = LocalDate.now();
        this.torneos = entrenador.getTorneosEntrenador(); // Obtenemos la lista de torneos
    }

    public boolean ejecutar() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = factory.newDocumentBuilder();
            DOMImplementation domImplementation = docBuilder.getDOMImplementation();
            Document document = domImplementation.createDocument(null, "carnet", null);
            crearXML(document);
            return true;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void crearXML(Document doc) {
        doc.setXmlVersion("1.0");

        LocalDate ld = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        String dateString = ld.format(dateTimeFormatter);
        String dateStrinUsr = fechaCreacion.format(dateTimeFormatter);

       
        crearElemento("id", Long.toString(getIdEntrenador()), doc, doc.getDocumentElement());
        crearElemento("fechaexp", dateString, doc, doc.getDocumentElement());

        Element elementEntrenador = doc.createElement("entrenador");
        doc.getDocumentElement().appendChild(elementEntrenador);
        crearElemento("nombre", nombre, doc, elementEntrenador);
        crearElemento("nacionalidad", nacionalidad, doc, elementEntrenador);
        crearElemento("hoy", dateStrinUsr, doc, elementEntrenador);
        crearElemento("puntos", Long.toString(puntos), doc, elementEntrenador);

        Element elementoTorneos = doc.createElement("torneos");
        doc.getDocumentElement().appendChild(elementoTorneos);

        List<Torneo> torneosAgregados = new ArrayList<>();

        for (Torneo torneo : torneos) {
            boolean yaAgregado = false;
            for (Torneo torneoAgregado : torneosAgregados) {
                if (torneoAgregado.getNombre().equals(torneo.getNombre())) {
                    yaAgregado = true;
                    break;
                }
            }
            if (!yaAgregado) {
                torneosAgregados.add(torneo); 
                Element torneoElement = doc.createElement("torneo");
                elementoTorneos.appendChild(torneoElement);
                crearElemento("nombre", torneo.getNombre(), doc, torneoElement);
                List<Combate> combates = obtenerCombatesPorTorneo(torneo);
               
                for (Combate combate : combates) {
                    Element combateElement = doc.createElement("combate");
                    torneoElement.appendChild(combateElement);
                    crearElemento("id", Integer.toString(combate.getId()), doc, combateElement);
                    crearElemento("fecha", combate.getFecha().toString(), doc, combateElement);
                    crearElemento("resultado", "Victoria", doc, combateElement);
                }
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

    private List<Combate> obtenerCombatesPorTorneo(Torneo torneo) {
        List<Combate> combatesDelEntrenador = entrenador.getCombatesEntrenador();
        List<Combate> combatesDelEntrenadorPorTorneo = new ArrayList<>();

        for(int k = 0; k <= combatesDelEntrenador.size() - 1; k++){
            if(combatesDelEntrenador.get(k).getIdTorneo() == torneo.getId()){
                combatesDelEntrenadorPorTorneo.add(combatesDelEntrenador.get(k));
            }
        }
        return combatesDelEntrenadorPorTorneo;
    }

    private static void crearElemento(String dato, String valor, Document document, Element raiz) {
        Element element = document.createElement(dato);
        Text text = document.createTextNode(valor);
        raiz.appendChild(element);
        element.appendChild(text);
    }

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

    public List<Torneo> getTorneos() {
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
}
