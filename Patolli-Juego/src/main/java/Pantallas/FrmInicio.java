package Pantallas;

import servidor.Cliente;
import servidor.Servidor;
import entidades.EstadoDelJuego;
import entidades.Jugador;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Enrique Rodriguez
 */
public class FrmInicio extends javax.swing.JFrame {

    FrmConfigurarPartida crearPartida;
    private static FrmInicio menuS;
    private Servidor servidor;

    /**
     * Creates new form PantallaInicio
     */
    public FrmInicio() {
        initComponents();
        crearPartida = new FrmConfigurarPartida();
        servidor = new Servidor();
        // Conectar automáticamente al servidor cuando se inicia la interfaz
//        iniciarServidor();

    }

    public static FrmInicio getInstance() {
        if (menuS == null) {
            menuS = new FrmInicio();
        }
        return menuS;
    }

    public void conectarJugador() {

        if (Servidor.getInstance().isServerInitialized()) {

            String codigoPartida = "HolaMundo2307";
            if (codigoPartida.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingresa un código de partida.");
                return;
            }

            Jugador jugador = new Jugador("Jugador");

            boolean exito = Servidor.getInstance().unirsePartida(codigoPartida, jugador);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Jugador unido a la partida con éxito.");
                new FrmTablero().setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo unir a la partida. Verifica el código.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "El servidor no está en ejecución.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnReglas = new javax.swing.JButton();
        btnUnirse = new javax.swing.JButton();
        btnSalir1 = new javax.swing.JButton();
        btnIniciarPartida = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnReglas.setBackground(new java.awt.Color(153, 0, 0));
        btnReglas.setFont(new java.awt.Font("STXinwei", 1, 18)); // NOI18N
        btnReglas.setForeground(new java.awt.Color(255, 255, 255));
        btnReglas.setText("Reglas del Juego");
        btnReglas.setBorder(null);
        btnReglas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReglasActionPerformed(evt);
            }
        });
        jPanel1.add(btnReglas, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 480, 160, 40));

        btnUnirse.setBackground(new java.awt.Color(153, 0, 0));
        btnUnirse.setFont(new java.awt.Font("STXinwei", 1, 18)); // NOI18N
        btnUnirse.setForeground(new java.awt.Color(255, 255, 255));
        btnUnirse.setText("Unirse a partida");
        btnUnirse.setBorder(null);
        btnUnirse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnirseActionPerformed(evt);
            }
        });
        jPanel1.add(btnUnirse, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 220, 70));

        btnSalir1.setBackground(new java.awt.Color(153, 0, 0));
        btnSalir1.setFont(new java.awt.Font("STXinwei", 1, 18)); // NOI18N
        btnSalir1.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir1.setText("Salir");
        btnSalir1.setBorder(null);
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 410, 220, 70));

        btnIniciarPartida.setBackground(new java.awt.Color(153, 0, 0));
        btnIniciarPartida.setFont(new java.awt.Font("STXinwei", 1, 18)); // NOI18N
        btnIniciarPartida.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarPartida.setText("Crear partida");
        btnIniciarPartida.setBorder(null);
        btnIniciarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarPartidaActionPerformed(evt);
            }
        });
        jPanel1.add(btnIniciarPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 220, 70));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("STXinwei", 1, 100)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Patolli");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 360, 90));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pajaro.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 210, 160));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/vizu.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 370, 210, 160));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tortuga.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 310, 170, 160));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/chango.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, -60, 250, 230));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sol.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, -20, 310, 230));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pez.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, -20, 290, 200));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/rana.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, 200, 150));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 540));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarPartidaActionPerformed
        try {
            new Thread(() -> {
                try {

                    Socket socket = new Socket("localhost", 50065);
                    JOptionPane.showMessageDialog(this, "Conexión exitosa a la partida.");

                    FrmConfigurarPartida configurarPartida = new FrmConfigurarPartida();
                    configurarPartida.setVisible(true);
                    this.dispose();
                } catch (IOException e) {

                    JOptionPane.showMessageDialog(this, "Error al conectarse a la partida: " + e.getMessage());
                }
            }).start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage());
        }
    }//GEN-LAST:event_btnIniciarPartidaActionPerformed

    private void btnReglasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReglasActionPerformed
        // TODO add your handling code here:
        dispose();
        DlgReglas control = new DlgReglas();
        control.setVisible(true);
    }//GEN-LAST:event_btnReglasActionPerformed

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
        // TODO add your handling code here:
        String[] botones = {"Si", "No"};

        int variable = JOptionPane.showOptionDialog(null, "¿Desea cerrar el juego?",
                "Confirmación", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null/*icono*/, botones, botones[0]);
        if (variable == 0) {
            dispose();
        } else {
            return;
        }
    }//GEN-LAST:event_btnSalir1ActionPerformed

    private void btnUnirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnirseActionPerformed
        // Crear un nuevo hilo para la conexión al servidor
        new Thread(() -> {
            try {

                Socket socket = new Socket("localhost", 50065);
                JOptionPane.showMessageDialog(this, "Conexión exitosa a la partida.");

                FrmUnirse frmUnirsePartida = new FrmUnirse();
                frmUnirsePartida.setVisible(true);
                this.dispose();
            } catch (IOException e) {

                JOptionPane.showMessageDialog(this, "Error al conectarse a la partida: " + e.getMessage());
            }
        }).start();

//        btnUnirse.addActionListener(e ->{
//                    int variable = JOptionPane.showOptionDialog(null, "Ingrese el codigo", "Ingresar", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, boton, );
//            String codigo = txtCodigo.getText();
//            Jugador jugador = new Jugador("Jugador: " );
//            if(servidor.unirsePartida(codigo, jugador)){
//                JOptionPane.showMessageDialog(null, " Te uniste");
//            }else{
//                JOptionPane.showMessageDialog(null, "Codigo incorrecto");
//            }
//        });
    }//GEN-LAST:event_btnUnirseActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarPartida;
    private javax.swing.JButton btnReglas;
    private javax.swing.JButton btnSalir1;
    private javax.swing.JButton btnUnirse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
