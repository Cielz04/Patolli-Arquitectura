package Control;

import PatolliCliente.ClienteControlador;
import Pantallas.DlgApuesta;
import Pantallas.FrmInicio;
import Pantallas.FrmTablero;
import Pantallas.FrmUnirse;
import java.util.List;
import javax.swing.JDialog;

/**
 *
 * @author esmeraldamolinaestrada
 */
public class ControlPantalla implements IControlPantalla{

    @Override
    public void PasarPantallaUnirseCrear(FrmInicio inicio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void PasarPantallaFinal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void PasarPantallaComoJugar(JDialog children) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void PasarPantallaSala() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void PasarPantallaOpciones() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void PasarPantallaInicio() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void PasarPantallaTablero(ClienteControlador controlPatolli) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    FrmUnirse unirse;
//    FrmInicio inicio;
//
//   
//
//    @Override
//    public void PasarPantallaUnirseCrear(FrmInicio inicio) {
//        this.inicio = inicio;
//        unirse.setVisible(true);
//        inicio.dispose();
//    }
//
//    @Override
//    public void PasarPantallaFinal() {
//        
//    }
//
//    @Override
//    public void PasarPantallaComoJugar(JDialog children) {
//        
//    }
//
//    @Override
//    public void PasarPantallaSala() {
//        
//    }
//
//    @Override
//    public void PasarPantallaOpciones() {
////        DlgApuesta pantallaApuesta = new DlgApuesta();
////        pantallaApuesta.setVisible(true);
//        
//    }
//
//    @Override
//    public void PasarPantallaInicio() {
//       
//    }
//
//    @Override
//    public void PasarPantallaTablero(ClienteControlador controlPatolli) {
//        FrmTablero tablero = new FrmTablero(controlPatolli, "000");
//        tablero.setVisible(true);
//    }
}
