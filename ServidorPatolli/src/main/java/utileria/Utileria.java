package utileria;

import java.util.Random;

/**
 *
 * @author Hector Espinoza
 */
public class Utileria {
    
    
    public static String generarNombre(){
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder nombre = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(caracteres.length());
            nombre.append(caracteres.charAt(index));
        }

        return nombre.toString();
    }
    
    
}
