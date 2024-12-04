/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Pantallas;

<<<<<<< refs/remotes/origin/melle
=======
import Cliente.Cliente;
import entidades.EstadoDelJuego;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Servidor.ControladorDeJuego;

import javax.swing.BorderFactory;
import tablero.Casilla;
import tablero.Tablero;

>>>>>>> local
/**
 *
 * @author Enrique Rodriguez
 */
public class FrmTablero extends javax.swing.JFrame {

<<<<<<< refs/remotes/origin/melle
=======
    private Cliente cliente;
    private static FrmTablero tableroS;
    private boolean numerarCasillas;
    private Tablero tablero;
    private int numeroCasilla;
    private final List<JLabel> casillas;
    private ControladorDeJuego controladorDeJuego;
    EstadoDelJuego estadoDelJuego;

>>>>>>> local
    /**
     * Creates new form Tablero
     */
    public FrmTablero() {
<<<<<<< refs/remotes/origin/melle
=======
        casillas = new LinkedList<>();
        estadoDelJuego = new EstadoDelJuego();
        controladorDeJuego = new ControladorDeJuego(estadoDelJuego);

>>>>>>> local
        initComponents();
        // Inicialización del cliente para la comunicación
        cliente = new Cliente("localhost", 50065);
        try {
            cliente.conectar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al conectar con el servidor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

<<<<<<< refs/remotes/origin/melle
=======
    public static FrmTablero getInstance() {
        if (tableroS == null) {
            tableroS = new FrmTablero();
        }
        return tableroS;
    }

    public void recibirActualizacionesDelServidor() {
        new Thread(() -> {
            try {
                while (true) {
                    String mensaje = cliente.recibirMensaje();
                    if (mensaje != null) {
                        procesarMensaje(mensaje);
                    }
                }
            } catch (Exception e) {
                System.err.println("Error al recibir mensaje del servidor: " + e.getMessage());
            }
        }).start();
    }

    // Procesar el mensaje recibido del servidor
    private void procesarMensaje(String mensaje) {
        if (mensaje.startsWith("ACTUALIZAR_TABLERO:")) {
            // Actualiza el tablero con la información recibida
            String datos = mensaje.substring("ACTUALIZAR_TABLERO:".length());
            // Actualiza las casillas o el tablero aquí
        } else if (mensaje.startsWith("CAMBIO_TURNO:")) {
            // Lógica para cambiar el turno
        }
    }

    private void enviarAccion(String accion) {
        if (cliente != null) {
            cliente.enviarMensaje("ACCION:" + accion); // Envía la acción al servidor
        }
    }

    /**
     * Metodo que inicializa el tablero estableciando medidas y generando las
     * casillas
     */
    /**
     * Metodo que inicializa el tablero estableciendo medidas y generando las
     * casillas
     */
    public void inicializar() {
        if (estadoDelJuego.getTablero() != null) {
            
            tablero = estadoDelJuego.getTablero();
            
            LinkedList<Casilla> casillasDelTablero = tablero.getCasillas();

            // Crear un panel de tablero con un GridLayout
            JPanel tableroPanel = new JPanel(new GridLayout(5, 5)); // Ajusta las filas y columnas según tu tablero
            tableroPanel.setPreferredSize(new Dimension(500, 500));

            // Limpiar las casillas previas
            casillas.clear();

            // Agregar las casillas al panel
            for (int i = 0; i < casillasDelTablero.size(); i++) {
                Casilla casilla = casillasDelTablero.get(i);
                JLabel casillaLabel = new JLabel();

                casillaLabel.setText(String.valueOf(i + 1));  // Numerar las casillas (si es necesario)
                casillaLabel.setOpaque(true);
                casillaLabel.setBackground(Color.WHITE);
                casillaLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                // Lógica de colores o iconos para las casillas
                if (i % 2 == 0) {
                    casillaLabel.setBackground(Color.RED);  // Ejemplo de colores alternados
                }

                tableroPanel.add(casillaLabel);  // Añadir casilla al panel
                casillas.add(casillaLabel);  // Guardar las casillas en la lista
            }

            // Añadir el panel con las casillas al JFrame
            getContentPane().add(tableroPanel, BorderLayout.CENTER);

            // Revalidar y repintar el panel para asegurar que se actualice
            revalidate();
            repaint();
        } else {
            JOptionPane.showMessageDialog(this, "El tablero no ha sido configurado correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo que dibuja el tablero
     */
    private void inicializarAspa(JPanel tablero, int filas, int columnas, boolean invertir) {
        tablero.setLayout(new GridLayout(filas, columnas));
        tablero.setPreferredSize(tablero.getSize());
        tablero.setMinimumSize(tablero.getSize());
        tablero.setMaximumSize(tablero.getSize());

        for (int i = 1; i <= filas * columnas; i++) {
            JLabel label = new JLabel(""); // Crea un nuevo JLabel
            label.setBorder(new LineBorder(Color.BLACK, 1)); // Añadir borde negro
            label.setOpaque(true); // Hacer el fondo visible
            label.setBackground(Color.WHITE);
            if (numerarCasillas) {
                label.setText(String.valueOf(numeroCasilla));
                numeroCasilla++;
            }

            // Lógica para asignar colores a las casillas del tablero
            if (filas * columnas > 6) {
                // Colocar casilla inicial (Amarilla)
                if (invertir) {
                    if (columnas > filas) {
                        if (i == columnas + 1) {
                            label.setBackground(Color.RED); // DERECHA
                        }
                    } else {
                        if (i == 1) {
                            label.setBackground(Color.RED); // ABAJO
                        }
                    }
                } else {
                    if (columnas > filas) {
                        if (i == columnas) {
                            label.setBackground(Color.RED); // IZQUIERDA
                        }
                    } else {
                        if (i == filas * columnas) {
                            label.setBackground(Color.RED); // ARRIBA
                        }
                    }
                }

                // Colocar casilla doble turno (Azul)
                if (invertir) {
                    if (columnas > filas) {
                        if (i == columnas || i == columnas * filas) {
                            label.setBackground(Color.getHSBColor(0.33f, 0.3f, 0.8f));// DERECHA
                        }
                    } else {
                        if (i == filas * columnas || i == filas * columnas - 1) {
                            label.setBackground(Color.getHSBColor(0.33f, 0.3f, 0.8f)); // ABAJO 104, 28, 76
                        }
                    }
                } else {
                    if (columnas > filas) {
                        if (i == 1 || i == columnas + 1) {
                            label.setBackground(Color.getHSBColor(0.33f, 0.3f, 0.8f)); // IZQUIERDA
                        }
                    } else {
                        if (i == 1 || i == 2) {
                            label.setBackground(Color.getHSBColor(0.33f, 0.3f, 0.8f)); // ARRIBA
                        }
                    }
                }

                // Colocar casilla pagar apuesta (ROJA)
                if (invertir) {
                    if (columnas > filas) {
                        if (i == columnas - 3 || i == columnas * filas - 3) {
                            label.setBackground(Color.getHSBColor(0.08f, 0.7f, 0.5f)); // DERECHA
                        }
                    } else {
                        if (i == filas * columnas - 7 || i == filas * columnas - 6) {
                            label.setBackground(Color.getHSBColor(0.08f, 0.7f, 0.5f)); // ABAJO
                        }
                    }
                } else {
                    if (columnas > filas) {
                        if (i == 4 || i == columnas + 4) {
                            label.setBackground(Color.getHSBColor(0.08f, 0.7f, 0.5f));  // IZQUIERDA
                        }
                    } else {
                        if (i == 7 || i == 8) {
                            label.setBackground(Color.getHSBColor(0.08f, 0.7f, 0.5f));  // ARRIBA
                        }
                    }
                }
            }

            tablero.add(label);
            casillas.add(label);
        }
    }

    private void agregarFicha(JLabel casillaBase, String rutaImagen) {
        try {
            ImageIcon icono = new ImageIcon(getClass().getResource(rutaImagen));
            Image imagenOriginal = icono.getImage();

            int nuevoAncho = 70;
            int nuevoAlto = 100;
            Image imagenEscalada = imagenOriginal.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);

            JLabel ficha = new JLabel(new ImageIcon(imagenEscalada));
            ficha.setHorizontalAlignment(JLabel.CENTER);
            ficha.setVerticalAlignment(JLabel.CENTER);

            casillaBase.setLayout(new BorderLayout());
            casillaBase.add(ficha, BorderLayout.CENTER);

            casillaBase.revalidate();
            casillaBase.repaint();
        } catch (NullPointerException e) {
            System.err.println("No se pudo cargar la imagen: " + rutaImagen);
            e.printStackTrace();
        }
    }

>>>>>>> local
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(810, 530));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(243, 243, 243)
                .addComponent(jLabel1)
                .addContainerGap(530, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(jLabel1)
                .addContainerGap(333, Short.MAX_VALUE))
        );

<<<<<<< refs/remotes/origin/melle
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 530));
=======
        txtCodigo.setFont(new java.awt.Font("STIX Two Text", 1, 14)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("STIX Two Text", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Codigo de la partida:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(294, Short.MAX_VALUE)
                        .addComponent(tableroIzq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblCania1)
                                .addGap(18, 18, 18)
                                .addComponent(lblCania2)
                                .addGap(15, 15, 15)
                                .addComponent(lblCania3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblCania4)
                                .addGap(18, 18, 18)
                                .addComponent(lblCania5)
                                .addGap(16, 16, 16))
                            .addComponent(btnLanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRendirse, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tableroArriba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tableroCentro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(tableroAbajo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(tableroDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(195, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnRendirse, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(232, 232, 232))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tableroArriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblCania5)
                                    .addComponent(lblCania4)
                                    .addComponent(lblCania3)
                                    .addComponent(lblCania2)
                                    .addComponent(lblCania1))
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(tableroIzq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(184, 184, 184)))
                        .addComponent(btnLanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tableroDerecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tableroCentro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addComponent(tableroAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
>>>>>>> local

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

<<<<<<< refs/remotes/origin/melle
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
            java.util.logging.Logger.getLogger(FrmTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmTablero().setVisible(true);
            }
        });
    }

=======
    private void btnLanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanzarActionPerformed
        int[] canias = new int[5];
        for (int i = 0; i < 5; i++) {
            canias[i] = (int) (Math.random() * 2);
        }
        escribirCanias(canias);
        // Enviar al servidor la acción de lanzamiento
        enviarAccion("LANZAR_FRIJOLES");
    }//GEN-LAST:event_btnLanzarActionPerformed

   private void escribirCanias(int[] canias) {
        if (canias[0] == 1) lblCania1.setText("•");
        if (canias[1] == 1) lblCania2.setText("•");
        if (canias[2] == 1) lblCania3.setText("•");
        if (canias[3] == 1) lblCania4.setText("•");
        if (canias[4] == 1) lblCania5.setText("•");
    }

    private void btnRendirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRendirseActionPerformed
         if (cliente != null) {
            // Obtener el nombre del jugador de alguna forma (esto dependerá de tu implementación)
            String nombreJugador = cliente.getClass().getName(); // Suponiendo que tienes un método para obtenerlo

            // Enviar la acción de rendirse al servidor
            cliente.enviarMensaje("RENDIRSE:" + nombreJugador);
        }

        // Cierra la ventana actual y abre la pantalla de inicio
        this.dispose();
        FrmInicio on = new FrmInicio();
        on.setVisible(true);
    }//GEN-LAST:event_btnRendirseActionPerformed


>>>>>>> local
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
