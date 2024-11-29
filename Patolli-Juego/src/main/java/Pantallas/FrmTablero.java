package Pantallas;

import Control.Connection;
import Control.ControlJugador;
import Control.ControlPatolli;
import Control.ServidorDelJuego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import tablero.Casilla;
import tablero.Tablero;

/**
 *
 * @author Enrique Rodriguez
 */
public class FrmTablero extends javax.swing.JFrame {

    private static FrmTablero tableroS;
    private boolean numerarCasillas=true;
    Tablero tablero = new Tablero();
    private int numeroCasilla;
    private final List<JLabel> casillas;
    private ServidorDelJuego servidor;
    private Connection jugador;
    private List<Integer> casillasOrdenadas;
    private int ultimoTiro = 0;

    /**
     * Creates new form Tablero
     */
    public FrmTablero() {
        casillas = new LinkedList<>();

        try {
            // Verificar si el puerto está ocupado
            if (!ServidorDelJuego.isRunning() && !ServidorDelJuego.isPortInUse(1002)) {
                // Si no está corriendo ni el puerto está ocupado, inicia el servidor
                servidor = ServidorDelJuego.getInstance();
                jugador = new Connection("localhost", 1002, "Jugador1");
                System.out.println("Servidor iniciado. Jugador conectado como Jugador1.");
            } else if (ServidorDelJuego.isPortInUse(1002)) {
                // Si el puerto está ocupado, conecta un nuevo cliente
                servidor = null; // No hay instancia local del servidor en este proceso
                jugador = new Connection("localhost", 1002, "Jugador");
                System.out.println("Conectado al servidor existente. Jugador conectado como Jugador.");
            } else {
                System.err.println("No se puede determinar el estado del servidor.");
            }
        } catch (Exception e) {
            System.err.println("Error al conectar con el servidor: " + e.getMessage());
        }

        initComponents(); // Inicializa los componentes de la interfaz gráfica
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
        inicializarAspa(tableroArriba, ControlPatolli.getInstance().getCasillasAspas(), 2, false);
        inicializarAspa(tableroAbajo, ControlPatolli.getInstance().getCasillasAspas(), 2, true);
        inicializarAspa(tableroDerecha, 2, ControlPatolli.getInstance().getCasillasAspas(), true);
        inicializarAspa(tableroIzq, 2, ControlPatolli.getInstance().getCasillasAspas(), false);

        inicializarAspa(tableroCentro, 2, 2, true);

//        for (int i = 0; i < tablero.getCasillas().size(); i++) {
//            tablero.getCasillas().get(i).addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    Casilla clickedLabel = (Casilla) e.getSource();
//                    moverFicha(clickedLabel, 3);
//                }
//            });
//        }
        asignarNumeroCasillas();
        // Ejemplo de uso
        Casilla casilla = tablero.getCasillas().get(0); // Obtener la primera casilla (por ejemplo)
        agregarFicha(casilla, "/Utilerias/ficha_roja.png");

    }

    private void moverFicha(Casilla casilla, int dado) {
        // Validar que la casilla es parte del tablero
        LinkedList<Casilla> listaCasillas = tablero.getCasillas();
        if (!listaCasillas.contains(casilla)) {
            System.out.println("La casilla no pertenece al tablero.");
            return;
        }

        // Verificar si la casilla seleccionada está ocupada (tiene una ficha)
        if (!casilla.isOcupada()) {  // Usamos el método de instancia isOcupada
            System.out.println("No hay una ficha en la casilla seleccionada.");
            return;  // Si no hay ficha, no se puede mover
        }

        // Encontrar la casilla inicial en la lista
        int indiceActual = listaCasillas.indexOf(casilla);
        if (indiceActual == -1) {
            System.out.println("Error: No se encontró la casilla en la lista.");
            return;
        }

        // Calcular la nueva posición
        int nuevaPosicion = (indiceActual + dado) % listaCasillas.size();

        // Obtener la casilla destino
        Casilla nuevaCasilla = listaCasillas.get(nuevaPosicion);

        // Validar si la nueva casilla está ocupada
        if (nuevaCasilla.isOcupada()) {  // Verificamos si la casilla destino está ocupada
            System.out.println("La casilla destino ya está ocupada.");
            return;
        }
        
        
        
        // Quitar la ficha de la casilla original (si tiene un icono)
        casilla.setIcon(null);  // Establecer un ícono vacío
        casilla.repaint();  // Forzar el repintado de la casilla original

        // Agregar la ficha a la nueva casilla
        agregarFicha(nuevaCasilla, "/Utilerias/ficha_roja.png");

        // Asegurarse de que la casilla destino se repinte
        nuevaCasilla.repaint();

        System.out.println("Ficha movida de casilla " + indiceActual + " a casilla " + nuevaPosicion + ".");
        ultimoTiro = 0;
        lblCania1.setText("-");
        lblCania2.setText("-");
        lblCania3.setText("-");
        lblCania4.setText("-");
        lblCania5.setText("-");

    }

    private void asignarNumeroCasillas() {
        tablero.ordenarCasillas(ControlPatolli.getInstance().getCasillasAspas(), tablero.getCasillas());
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
            Casilla label = new Casilla(""); // Crea un nuevo JLabel
            label.setBorder(new LineBorder(Color.BLACK, 1)); // Añadir borde negro
            label.setOpaque(true); // Hacer el fondo visible
            label.setBackground(Color.WHITE);

            // Agregar evento MouseListener
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    moverFicha(label, 1); // Mueve ficha al hacer clic
                }
            });

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

            // Añadir el JLabel al tablero y a la lista de casillas
            tablero.add(label);
            this.tablero.agregarCasilla(label);
        }
    }

    private void agregarFicha(Casilla casillaBase, String rutaImagen) {
        int nuevoAncho = 50;
        int nuevoAlto = 80;
        try {

            // Verificar si la casilla ya tiene un icono
            if (casillaBase.isOcupada()) {
                System.out.println("La casilla ya está ocupada.");
                return; // Si ya está ocupada, no agregues la ficha
            }

            // Cargar la imagen
            ImageIcon icono = new ImageIcon(getClass().getResource(rutaImagen));
            Image imagenOriginal = icono.getImage();

            System.out.println(casillaBase.getSize());

            if (casillaBase.getSize() == new Dimension(30, 76)) {
                nuevoAncho = 30;
                nuevoAlto = 60;
            }
            if (casillaBase.getSize() == new Dimension(21, 76)) {
                nuevoAncho = 5;
                nuevoAlto = 5;
            }

            Image imagenEscalada = imagenOriginal.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);

            // Establecer el nuevo icono
            casillaBase.setIcon(new ImageIcon(imagenEscalada));

            // Forzar repintado
            casillaBase.repaint();

            System.out.println("Ficha agregada a la casilla.");

        } catch (NullPointerException e) {
            System.err.println("No se pudo cargar la imagen: " + rutaImagen);
            e.printStackTrace();
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
        jPanel3 = new javax.swing.JPanel();
        btnRendirse = new javax.swing.JButton();
        btnLanzar = new javax.swing.JButton();
        lblCania1 = new javax.swing.JLabel();
        lblCania2 = new javax.swing.JLabel();
        lblCania4 = new javax.swing.JLabel();
        lblCania3 = new javax.swing.JLabel();
        lblCania5 = new javax.swing.JLabel();
        tableroIzq = new javax.swing.JPanel();
        tableroArriba = new javax.swing.JPanel();
        tableroAbajo = new javax.swing.JPanel();
        tableroDerecha = new javax.swing.JPanel();
        tableroCentro = new javax.swing.JPanel();

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

        tableroIzq.setBackground(new java.awt.Color(51, 0, 0));
        tableroIzq.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout tableroIzqLayout = new javax.swing.GroupLayout(tableroIzq);
        tableroIzq.setLayout(tableroIzqLayout);
        tableroIzqLayout.setHorizontalGroup(
            tableroIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );
        tableroIzqLayout.setVerticalGroup(
            tableroIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );

        tableroArriba.setBackground(new java.awt.Color(51, 0, 0));
        tableroArriba.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout tableroArribaLayout = new javax.swing.GroupLayout(tableroArriba);
        tableroArriba.setLayout(tableroArribaLayout);
        tableroArribaLayout.setHorizontalGroup(
            tableroArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );
        tableroArribaLayout.setVerticalGroup(
            tableroArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 254, Short.MAX_VALUE)
        );

        tableroAbajo.setBackground(new java.awt.Color(51, 0, 0));
        tableroAbajo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout tableroAbajoLayout = new javax.swing.GroupLayout(tableroAbajo);
        tableroAbajo.setLayout(tableroAbajoLayout);
        tableroAbajoLayout.setHorizontalGroup(
            tableroAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 171, Short.MAX_VALUE)
        );
        tableroAbajoLayout.setVerticalGroup(
            tableroAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 254, Short.MAX_VALUE)
        );

        tableroDerecha.setBackground(new java.awt.Color(51, 0, 0));
        tableroDerecha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout tableroDerechaLayout = new javax.swing.GroupLayout(tableroDerecha);
        tableroDerecha.setLayout(tableroDerechaLayout);
        tableroDerechaLayout.setHorizontalGroup(
            tableroDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );
        tableroDerechaLayout.setVerticalGroup(
            tableroDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tableroCentro.setBackground(new java.awt.Color(51, 0, 0));
        tableroCentro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout tableroCentroLayout = new javax.swing.GroupLayout(tableroCentro);
        tableroCentro.setLayout(tableroCentroLayout);
        tableroCentroLayout.setHorizontalGroup(
            tableroCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        tableroCentroLayout.setVerticalGroup(
            tableroCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(370, Short.MAX_VALUE)
                        .addComponent(tableroIzq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tableroArriba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tableroCentro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(tableroAbajo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableroDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
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
                        .addComponent(tableroArriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanzarActionPerformed

//        if () {
        int[] canias = new int[5];
        int i = 0;
        ultimoTiro = 0;

        while (i < 5) {
            canias[i] = (int) (Math.random() * 2);
            if (canias[i] == 1) {
                ultimoTiro++;
            }
            i++;
        }
        escribirCanias(canias);
//        }
    }//GEN-LAST:event_btnLanzarActionPerformed

    private void escribirCanias(int canias[]) {
        if (canias[0] == 1) {
            lblCania1.setText("•");
        }
        if (canias[1] == 1) {
            lblCania2.setText("•");
        }
        if (canias[2] == 1) {
            lblCania3.setText("•");
        }
        if (canias[3] == 1) {
            lblCania4.setText("•");
        }
        if (canias[4] == 1) {
            lblCania5.setText("•");
        }

    }

    private void btnRendirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRendirseActionPerformed
        this.dispose();
        FrmInicio on = new FrmInicio();
        on.setVisible(true);
    }//GEN-LAST:event_btnRendirseActionPerformed


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
    private javax.swing.JPanel tableroAbajo;
    private javax.swing.JPanel tableroArriba;
    private javax.swing.JPanel tableroCentro;
    private javax.swing.JPanel tableroDerecha;
    private javax.swing.JPanel tableroIzq;
    // End of variables declaration//GEN-END:variables
}
