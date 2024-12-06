package com.proyectopokemonadt.complementarias;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.proyectopokemonadt.ENTIDADES.Torneo;

public class ArchivadorTorneos {
    public static void Archivador (Torneo torneo){
        File file = new File("proyectopokemonadt\\src\\main\\java\\com\\proyectopokemonadt\\archviosComplementarios\\torneos.dat");

        try {
            ObjectOutputStream oou = new ObjectOutputStream(new DataOutputStream(new FileOutputStream(file, true)));
            oou.writeObject(torneo);
            oou.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
