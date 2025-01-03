package Pantallas;

import PatolliCliente.ClienteControlador;
import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.MessageBody;
import com.chat.tcpcommons.MessageType;
import entidades.Jugador;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author molin
 */
public class FrmConfigurarPartida extends javax.swing.JFrame {

    /**
     * Creates new form FrmConfigurarPartida
     */
    private ClienteControlador cliente;

    public FrmConfigurarPartida() {
        initComponents();
        btnGroupAspas.add(aspa8);
        btnGroupAspas.add(aspa10);
        btnGroupAspas.add(aspa14);
        btnGroupFichas.add(fichas2);
        btnGroupFichas.add(fichas4);
        btnGroupFichas.add(fichas6);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        btnGroupAspas = new javax.swing.ButtonGroup();
        btnGroupFichas = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        aspa8 = new javax.swing.JRadioButton();
        aspa10 = new javax.swing.JRadioButton();
        aspa14 = new javax.swing.JRadioButton();
        txtSubTitulo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        fichas2 = new javax.swing.JRadioButton();
        fichas4 = new javax.swing.JRadioButton();
        fichas6 = new javax.swing.JRadioButton();
        btnApuesta = new javax.swing.JButton();
        txtSubTitulo1 = new javax.swing.JLabel();
        txtTituloConf = new javax.swing.JLabel();
        txtImgTortugaConf = new javax.swing.JLabel();
        txtImgRanaConf = new javax.swing.JLabel();
        txtImgChangoConf = new javax.swing.JLabel();
        txtImgPezConf = new javax.swing.JLabel();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 0, 0));
        jPanel1.setForeground(new java.awt.Color(102, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 0, 0));

        jLabel6.setFont(new java.awt.Font("STZhongsong", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Numero de fichas");

        jPanel3.setBackground(new java.awt.Color(153, 0, 0));

        aspa8.setFont(new java.awt.Font("STXinwei", 1, 14)); // NOI18N
        aspa8.setForeground(new java.awt.Color(255, 255, 255));
        aspa8.setText("8 por aspa");
        aspa8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aspa8ActionPerformed(evt);
            }
        });

        aspa10.setFont(new java.awt.Font("STXinwei", 1, 14)); // NOI18N
        aspa10.setForeground(new java.awt.Color(255, 255, 255));
        aspa10.setText("10 por aspa");
        aspa10.setEnabled(false);
        aspa10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aspa10ActionPerformed(evt);
            }
        });

        aspa14.setFont(new java.awt.Font("STXinwei", 1, 14)); // NOI18N
        aspa14.setForeground(new java.awt.Color(255, 255, 255));
        aspa14.setText("14 por aspa");
        aspa14.setEnabled(false);
        aspa14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aspa14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(aspa8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(aspa10, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(aspa14, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aspa8)
                    .addComponent(aspa10)
                    .addComponent(aspa14))
                .addContainerGap())
        );

        txtSubTitulo.setFont(new java.awt.Font("STZhongsong", 1, 18)); // NOI18N
        txtSubTitulo.setForeground(new java.awt.Color(255, 255, 255));
        txtSubTitulo.setText("Realiza tu apuesta");

        jPanel4.setBackground(new java.awt.Color(153, 0, 0));

        fichas2.setFont(new java.awt.Font("STXinwei", 1, 14)); // NOI18N
        fichas2.setForeground(new java.awt.Color(255, 255, 255));
        fichas2.setText("2 Fichas");

        fichas4.setFont(new java.awt.Font("STXinwei", 1, 14)); // NOI18N
        fichas4.setForeground(new java.awt.Color(255, 255, 255));
        fichas4.setText("4 Fichas");
        fichas4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fichas4ActionPerformed(evt);
            }
        });

        fichas6.setFont(new java.awt.Font("STXinwei", 1, 14)); // NOI18N
        fichas6.setForeground(new java.awt.Color(255, 255, 255));
        fichas6.setText("6 Fichas");
        fichas6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fichas6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(fichas2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(fichas4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111)
                .addComponent(fichas6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fichas2)
                    .addComponent(fichas4)
                    .addComponent(fichas6))
                .addContainerGap())
        );

        btnApuesta.setBackground(new java.awt.Color(51, 0, 0));
        btnApuesta.setFont(new java.awt.Font("STXinwei", 0, 14)); // NOI18N
        btnApuesta.setForeground(new java.awt.Color(255, 255, 255));
        btnApuesta.setText("Apuesta");
        btnApuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApuestaActionPerformed(evt);
            }
        });

        txtSubTitulo1.setFont(new java.awt.Font("STZhongsong", 1, 18)); // NOI18N
        txtSubTitulo1.setForeground(new java.awt.Color(255, 255, 255));
        txtSubTitulo1.setText("Tamaño del tablero");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(305, 305, 305)
                        .addComponent(btnApuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(txtSubTitulo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtSubTitulo1)
                        .addGap(279, 279, 279))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(292, 292, 292))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(txtSubTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSubTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(btnApuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 750, 410));

        txtTituloConf.setBackground(new java.awt.Color(255, 255, 255));
        txtTituloConf.setFont(new java.awt.Font("STXinwei", 1, 36)); // NOI18N
        txtTituloConf.setForeground(new java.awt.Color(255, 255, 255));
        txtTituloConf.setText("CONFIGURACION DE PARTIDA");
        jPanel1.add(txtTituloConf, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 570, 110));

        txtImgTortugaConf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tortuga.png"))); // NOI18N
        jPanel1.add(txtImgTortugaConf, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, -10, 170, 190));

        txtImgRanaConf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/rana.png"))); // NOI18N
        jPanel1.add(txtImgRanaConf, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, -20, 130, 190));

        txtImgChangoConf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/chango.png"))); // NOI18N
        jPanel1.add(txtImgChangoConf, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 390, 190, 140));

        txtImgPezConf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pez.png"))); // NOI18N
        jPanel1.add(txtImgPezConf, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 460, 190, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void aspa10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aspa10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aspa10ActionPerformed

    private void aspa14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aspa14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aspa14ActionPerformed

    private void fichas4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fichas4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fichas4ActionPerformed

    private void fichas6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fichas6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fichas6ActionPerformed

    private void btnApuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApuestaActionPerformed
        try {
            // Determinar el tamaño del aspa basado en la selección
            int tamanio = aspa8.isSelected() ? 8 : aspa10.isSelected() ? 10 : 14;

            // Determinar la cantidad de fichas basado en la selección
            int fichas = fichas2.isSelected() ? 2 : fichas4.isSelected() ? 4 : 6;

            cliente = new ClienteControlador(new Jugador("Jugador 1", Color.RED), true);
            
            // Establecer la configuración en el tablero local del cliente
            cliente.getTableroLocal().setCantidadCasillasAspa(tamanio);
            cliente.getTableroLocal().setCantidadFichas(fichas);
            
            MessageBody body = new MessageBody();
            
            body.setCantidadCasillasAspa(tamanio);
            body.setCantidadFichas(fichas);

            // Enviar la configuración del tablero al servidor
            Message mensajeConfiguracion = new Message.Builder()
                    .messageType(MessageType.CONFIGURAR_TABLERO) // Tipo de mensaje para configurar el tablero
                    .sender(cliente.getJugador()) // Jugador que envía la solicitud
                    .body(body) // Contiene el tablero local con la nueva configuración
                    .build();

            cliente.enviarMensaje(mensajeConfiguracion);

            // Confirmar la configuración en el cliente
            JOptionPane.showMessageDialog(this, "Configuración enviada al servidor:\n"
                    + "Tamaño de aspa: " + tamanio + "\n"
                    + "Cantidad de fichas: " + fichas);

            // Mostrar el diálogo de apuestas
            DlgApuesta dlgApuesta = new DlgApuesta(cliente);
            dlgApuesta.setVisible(true);

            // Cerrar la ventana actual
            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al configurar: " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnApuestaActionPerformed

    private void aspa8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aspa8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aspa8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton aspa10;
    private javax.swing.JRadioButton aspa14;
    private javax.swing.JRadioButton aspa8;
    private javax.swing.JButton btnApuesta;
    private javax.swing.ButtonGroup btnGroupAspas;
    private javax.swing.ButtonGroup btnGroupFichas;
    private javax.swing.JRadioButton fichas2;
    private javax.swing.JRadioButton fichas4;
    private javax.swing.JRadioButton fichas6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel txtImgChangoConf;
    private javax.swing.JLabel txtImgPezConf;
    private javax.swing.JLabel txtImgRanaConf;
    private javax.swing.JLabel txtImgTortugaConf;
    private javax.swing.JLabel txtSubTitulo;
    private javax.swing.JLabel txtSubTitulo1;
    private javax.swing.JLabel txtTituloConf;
    // End of variables declaration//GEN-END:variables
}
