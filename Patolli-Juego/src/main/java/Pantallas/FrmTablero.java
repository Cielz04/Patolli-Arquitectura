package Pantallas;

import dibujado.TableroCanvas;
import entidades.Juego;
import entidades.Tablero;
import java.awt.Dimension;

/**
 *
 * @author Enrique Rodriguez
 */
public class FrmTablero extends javax.swing.JFrame {

    private TableroCanvas tbCanvas;
    private static FrmTablero tableroS;

    Tablero tablero = new Tablero();

    /**
     * Creates new form Tablero
     */
    public FrmTablero() {
        initComponents();
    }

    public static FrmTablero getInstance() {
        if (tableroS == null) {
            tableroS = new FrmTablero();
        }

        return tableroS;
    }

    /**
     * Metodo que inicializa el tablero estableciando medidas y generando las
     * casillas
     */
    public void inicializar() {
//        this.lbMontoApuesta.setText(Juego.getInstance().getApuesta() + "");

        tbCanvas = new TableroCanvas(tablero.getCasillas(), Juego.getInstance().getNumCasillasAspa(), this.getWidth());
        tablero.setCasillas(tbCanvas.generarCasillas());

        Juego.getInstance().setTablero(tablero);

        tbCanvas.setSize(600, 400);
        Dimension dim = super.getToolkit().getScreenSize();
        System.out.println(dim.getWidth() + "" + dim.getHeight());
        tbCanvas.setLocation((int) dim.getWidth() / 5, (int) dim.getHeight() / 9);
        jPanel3.add(tbCanvas);
    }

    /**
     * Metodo que dibuja el tablero
     */
    public void pintarTablero() {
        tbCanvas.setCasillas(Juego.getInstance().getTablero().getCasillas());
        this.repaint();

//		for (int i = 0; i < Juego.getInstance().getListaJugador().size(); i++) {
//			System.out.println(Juego.getInstance().getListaJugador().get(i).getNombre());
//		}
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        btnRendirse = new javax.swing.JButton();
        btnLanzar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tablero");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(163, 151, 124));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(51, 0, 0));

        btnRendirse.setBackground(new java.awt.Color(204, 153, 0));
        btnRendirse.setFont(new java.awt.Font("STXinwei", 1, 18)); // NOI18N
        btnRendirse.setForeground(new java.awt.Color(255, 255, 255));
        btnRendirse.setText("Rendirse");
        btnRendirse.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRendirse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRendirseActionPerformed(evt);
            }
        });

        btnLanzar.setBackground(new java.awt.Color(204, 153, 0));
        btnLanzar.setFont(new java.awt.Font("STXinwei", 1, 18)); // NOI18N
        btnLanzar.setForeground(new java.awt.Color(255, 255, 255));
        btnLanzar.setText("Lanzar Frijoles");
        btnLanzar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLanzarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnRendirse, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 882, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1234, 1234, 1234))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnRendirse, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 379, Short.MAX_VALUE)
                .addComponent(btnLanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 890, 510));
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(-90, -80, 320, 290));
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(-70, 260, 320, 330));
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, -110, 320, 290));
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, -100, 320, 330));
        jPanel2.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 740, 440));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 580));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanzarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLanzarActionPerformed

    private void btnRendirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRendirseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRendirseActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrmTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new FrmTablero().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLanzar;
    private javax.swing.JButton btnRendirse;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
