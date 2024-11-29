//package itson.mx.servidorpatolli;
//
//import Cliente.Cliente;
//import Cliente.Servidor;
//import java.io.IOException;
//import javax.swing.JOptionPane;
//
///**
// *
// * @author esmeraldamolinaestrada
// */
//public class ServidorPatolli {
//    private static Servidor instancia;
//
//    public static void main(String[] args) {
//        try {
//            // Verificar o crear la instancia única del servidor
//            instancia = Servidor.getInstance();
//
//            // Iniciar el servidor en un hilo separado
//            Thread servidorThread = new Thread(() -> {
//                try {
//                    instancia.iniciarServidor();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    JOptionPane.showMessageDialog(null, "Error al iniciar el servidor: " + e.getMessage());
//                }
//            });
//            servidorThread.start();
//
//            JOptionPane.showMessageDialog(null, "Servidor iniciado. Esperando jugadores...");
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Error general al iniciar el servidor: " + e.getMessage());
//        }
//    }
//
//}
