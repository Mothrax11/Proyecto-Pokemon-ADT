package com.proyectopokemonadt.autenticacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase encargada de leer el fichero id.txt, obteniendo así un número (ID) y
 * devolviéndoselo al método que lo llamó.
 * Después de devolver el número, este es incrementado en 1 y escrito nuevamente
 * en el fichero para futuras utilizaciones.
 * 
 * @author raullg97
 */
public class idGenerator {

    /**
     * Método que genera un ID único a partir del valor almacenado en el archivo
     * "id.txt".
     * 
     * El método lee el valor actual del ID desde el archivo "id.txt", lo incrementa
     * en 1
     * y lo escribe de nuevo en el archivo para la siguiente vez que se necesite.
     * Si el archivo no se encuentra, se maneja la excepción y se devuelve -1.
     * 
     * @return El próximo ID disponible. Si ocurre un error, devuelve -1.
     */
    public static int generador() {
        int id;

        // Ruta del archivo id.txt
        File f = new File("proyectopokemonadt/src/main/java/com/proyectopokemonadt/archviosComplementarios/id.txt");

        try {
            // Se abre el archivo para leer el ID actual
            BufferedReader br = new BufferedReader(new FileReader(f));

            try {
                // Se lee la primera línea, que contiene el ID actual
                id = Integer.valueOf(br.readLine());
                br.close();

                // Se abre el archivo nuevamente para escribir el nuevo ID incrementado
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                bw.write(String.valueOf(id + 1)); // Incrementa el ID
                bw.close();

                // Devuelve el ID actual antes de incrementarlo
                return id;
            } catch (IOException e) {
                // En caso de error al leer o escribir en el archivo, se imprime el error
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // Si el archivo no se encuentra, se imprime el error
            e.printStackTrace();
        }

        // Si ocurre un error, se devuelve -1
        return -1;
    }
}
