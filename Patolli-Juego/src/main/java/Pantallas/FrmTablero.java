package Pantallas;

import Control.ControlPatolli;
import dibujado.TableroCanvas;

import entidades.Tablero;
import java.awt.Color;
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
        tbCanvas = new TableroCanvas(tablero.getCasillas(), ControlPatolli.getInstance().getCasillasAspas(), jPanel3.getWidth());
        tablero.setCasillas(tbCanvas.generarCasillas());

        ControlPatolli.getInstance().setTablero(tablero);

        // Ajustamos el tamaño de tbCanvas al tamaño de jPanel3 o a un tamaño específico
        tbCanvas.setSize(jPanel3.getWidth(), jPanel3.getHeight());
        
        

        // Centramos tbCanvas dentro de jPanel3
        int xPos = (jPanel3.getWidth() - tbCanvas.getWidth()) / 2;
        int yPos = (jPanel3.getHeight() - tbCanvas.getHeight()) / 2;
        tbCanvas.setLocation(xPos, yPos);

        ControlPatolli control =  ControlPatolli.getInstance();
        
        jPanel3.add(tbCanvas);
        jPanel3.revalidate();
        jPanel3.repaint();
        tbCanvas.sacarFicha(tbCanvas.getCasillas().get(10), ControlPatolli.getInstance().getColorTurnoJugador());
    }

    /**
     * Metodo que dibuja el tablero
     */
    public void pintarTablero() {
        tbCanvas.setCasillas(ControlPatolli.getInstance().getTablero().getCasillas());
        this.repaint();

//		for (int i = 0; i < ControlPatolli.getInstance().getListaJugador().size(); i++) {
//			System.out.println  ControlPatolli.getInstance().getListaJugador().get(i).getNombre());
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
        jPanel3 = new javax.swing.JPanel();
        btnRendirse = new javax.swing.JButton();
        btnLanzar = new javax.swing.JButton();
        lblCania1 = new javax.swing.JLabel();
        lblCania2 = new javax.swing.JLabel();
        lblCania4 = new javax.swing.JLabel();
        lblCania3 = new javax.swing.JLabel();
        lblCania5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tablero");
        setBackground(new java.awt.Color(51, 0, 0));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(51, 0, 0));
        jPanel3.setMaximumSize(new java.awt.Dimension(1280, 720));
        jPanel3.setMinimumSize(new java.awt.Dimension(1280, 720));

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

        lblCania1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblCania1.setForeground(new java.awt.Color(255, 255, 255));
        lblCania1.setText("-");

        lblCania2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblCania2.setForeground(new java.awt.Color(255, 255, 255));
        lblCania2.setText("-");

        lblCania4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblCania4.setForeground(new java.awt.Color(255, 255, 255));
        lblCania4.setText("-");

        lblCania3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblCania3.setForeground(new java.awt.Color(255, 255, 255));
        lblCania3.setText("-");

        lblCania5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblCania5.setForeground(new java.awt.Color(255, 255, 255));
        lblCania5.setText("-");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(btnLanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRendirse, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1098, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnRendirse, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 499, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCania5)
                    .addComponent(lblCania4)
                    .addComponent(lblCania3)
                    .addComponent(lblCania2)
                    .addComponent(lblCania1))
                .addGap(8, 8, 8)
                .addComponent(btnLanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanzarActionPerformed
        int[] canias = new int[5];
        int i=0;
        
        while (i<5){
            canias[i] = (int) (Math.random() * 2); 
            i++;
        }
        escribirCanias(canias);
    }//GEN-LAST:event_btnLanzarActionPerformed

    private void escribirCanias(int canias[]){
        if (canias[0]==1){
            lblCania1.setText("•");
        }
        if (canias[1]==1){
            lblCania2.setText("•");
        }
        if (canias[2]==1){
            lblCania3.setText("•");
        }
        if (canias[3]==1){
            lblCania4.setText("•");
        }
        if (canias[4]==1){
            lblCania5.setText("•");
        }
    }
    
    private void btnRendirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRendirseActionPerformed
        this.dispose();
        FrmInicio on = new FrmInicio();
        on.setVisible(true);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblCania1;
    private javax.swing.JLabel lblCania2;
    private javax.swing.JLabel lblCania3;
    private javax.swing.JLabel lblCania4;
    private javax.swing.JLabel lblCania5;
    // End of variables declaration//GEN-END:variables
}
