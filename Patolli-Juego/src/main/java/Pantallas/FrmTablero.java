package Pantallas;



import PatolliCliente.ClienteControlador;
import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.MessageBody;
import com.chat.tcpcommons.MessageType;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import tablero.Casilla;
import tablero.Tablero;

/**
 *
 * @author Enrique Rodriguez
 */
public class FrmTablero extends javax.swing.JFrame {

   private static FrmTablero tableroS;  // Instancia estática de FrmTablero
    

    private List<Casilla> fichaJugador1;
    private List<Casilla> fichaJugador2;
    private List<Casilla> fichaJugador3;
    private List<Casilla> fichaJugador4;

    private int ultimoTiro = 0;
    
    private ClienteControlador clienteControlador;

    private List<Casilla> casillasTablero;
    private boolean numerarCasillas;
    private int numeroCasilla;
    private Tablero tableroLocal;


    // Constructor privado
    public FrmTablero(Tablero tablero, ClienteControlador clienteControlador) {
        casillasTablero = new LinkedList<>();
        this.tableroLocal = tablero;
        this.clienteControlador = clienteControlador;
        initComponents();
    }

    // Método estático para obtener la instancia única de FrmTablero
    public static FrmTablero getInstance(Tablero tablero, ClienteControlador clienteControlador) {
        if (tableroS == null) {
            tableroS = new FrmTablero(tablero, clienteControlador);
        }
        return tableroS;
    }

    // Aquí, el método de inicialización o manejo de las casillas, las posiciones, etc.
    public void inicializarTablero() {
        // Ejemplo de cómo inicializar las casillas, personaliza según tus necesidades
        casillasTablero = new ArrayList<>();
        // Aquí agregas la lógica para crear y añadir casillas a la lista
    }
    
    

    // Este método actualizará el estado visual del tablero
    public void redibujarTablero(Tablero nuevoTablero) {
        // Actualiza las casillas del tablero
        this.casillasTablero = nuevoTablero.getCasillas();
        
        // Actualiza las posiciones de las fichas de los jugadores
        this.tableroLocal.setFichasJugador1Posicion(nuevoTablero.getFichasJugador1Posicion()) ;
        this.tableroLocal.setFichasJugador2Posicion(nuevoTablero.getFichasJugador2Posicion()) ;
        this.tableroLocal.setFichasJugador3Posicion(nuevoTablero.getFichasJugador3Posicion()) ;
        this.tableroLocal.setFichasJugador4Posicion(nuevoTablero.getFichasJugador4Posicion()) ;
       

        // Actualiza el turno del jugador
        if (tableroLocal.getJugadorTurno()==1){
            tableroLocal.setJugadorTurno(2);
        }
        if (tableroLocal.getJugadorTurno()==2){
            if (tableroLocal.getCantidadJugadores()==2) {
                tableroLocal.setJugadorTurno(1);
            }else{
                tableroLocal.setJugadorTurno(3);   
            }
            
        }
        if (tableroLocal.getJugadorTurno()==3){
            if (tableroLocal.getCantidadJugadores()==3) {
                tableroLocal.setJugadorTurno(1);
            }else{
                tableroLocal.setJugadorTurno(4);
            }
        }
        if (tableroLocal.getJugadorTurno()==4){
            tableroLocal.setJugadorTurno(1);
        }

        // Redibujar el tablero, actualizando las casillas, fichas, etc.
        revalidate();
        repaint(); // Esto repinta la interfaz
    }

    /**
     * Metodo que inicializa el tablero estableciando medidas y generando las
     * casillas
     */
    public void inicializar() {
        inicializarAspa(tableroArriba, tableroLocal.getCantidadCasillasAspa(), 2, false);
        inicializarAspa(tableroAbajo, tableroLocal.getCantidadCasillasAspa(), 2, true);
        inicializarAspa(tableroDerecha, 2, tableroLocal.getCantidadCasillasAspa(), true);
        inicializarAspa(tableroIzq, 2, tableroLocal.getCantidadCasillasAspa(), false);

        inicializarAspa(tableroCentro, 2, 2, true);

        asignarNumeroCasillas();
        // Ejemplo de uso
        Casilla casilla = tableroLocal.getCasillas().get(0);
        agregarFicha(casilla, "/Utilerias/ficha_roja.png");

    }

    private void asignarNumeroCasillas() {
        tableroLocal.ordenarCasillas(tableroLocal.getCasillas());
    }

    private void moverFicha(Casilla casilla, int dado) {
        // Validar que la casilla es parte del tablero
        LinkedList<Casilla> listaCasillas = tableroLocal.getCasillas();
        if (!listaCasillas.contains(casilla)) {
            System.out.println("La casilla no pertenece al tablero.");
            return;
        }

        //Verificar si la casilla seleccionada está ocupada (tiene una ficha)
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
        casilla.setIcon(null);
        System.out.println("");

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
                    moverFicha(label, ultimoTiro); // Mueve ficha al hacer clic
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
            tableroLocal.agregarCasilla(label);
        }
    }

    private void agregarFicha(Casilla casillaBase, String rutaImagen) {

        try {
            // Cargar y escalar la imagen
            ImageIcon icono = new ImageIcon(getClass().getResource(rutaImagen));
            Image imagenOriginal = icono.getImage();
            int nuevoAncho = 70;
            int nuevoAlto = 100;
            Image imagenEscalada = imagenOriginal.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);

            // Crear la casilla con la ficha
            Casilla ficha = new Casilla(new ImageIcon(imagenEscalada));
            ficha.setHorizontalAlignment(JLabel.CENTER);
            ficha.setVerticalAlignment(JLabel.CENTER);

            // Configurar la casilla base para contener la ficha
            casillaBase.setLayout(new BorderLayout());
            casillaBase.add(ficha, BorderLayout.CENTER);
            casillaBase.revalidate();
            casillaBase.repaint();
            casillaBase.setIcon(icono);

            // Actualizar el tablero
            
            int index = tableroLocal.getCasillas().indexOf(casillaBase);
            tableroLocal.getCasillas().set(index, casillaBase);

        } catch (NullPointerException e) {
            System.err.println("No se pudo cargar la imagen: " + rutaImagen);
            e.printStackTrace();
        }

    }

    public void manejarEstadoTablero(Message mensaje) {
        if (mensaje.getMessageType() == MessageType.TABLERO_ACTUALIZADO) {
            MessageBody body = (MessageBody) mensaje.getContent();

            // Obtener el estado del tablero desde el mensaje recibido
            Tablero tableroRecibido = body.getEstadoTablero();

            // Actualizar el tablero en ControlPatolli
//            clienteControlador.setTableroLocal(tableroLocal);

            // Actualizar parámetros locales
//            this.canCasillasAspa = tableroRecibido.getCantidadCasillasAspa();
//            this.casillasTablero = tableroRecibido.getCasillas();

            // Inicializar el tablero visualmente
            redibujarTablero(tableroLocal);
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
        txtCodigo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

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

        txtCodigo.setFont(new java.awt.Font("STIX Two Text", 1, 14)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(255, 255, 255));
        txtCodigo.setText("HolaMundo2307");

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

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanzarActionPerformed

//        if () {
        int[] canias = new int[5];
        int i = 0;

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
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel txtCodigo;
    // End of variables declaration//GEN-END:variables
}
