package entidades;

import java.util.Random;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class Codigo {
     public static String generarCodigo() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder codigo = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 5; i++) { // Generar un código de 5 caracteres
            int index = random.nextInt(caracteres.length());
            codigo.append(caracteres.charAt(index));
        }

        return codigo.toString();
    }
}
