package Control;

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

    FrmUnirse unirse;
    FrmInicio inicio;

   

    @Override
    public void PasarPantallaUnirseCrear(FrmInicio inicio) {
        this.inicio = inicio;
        unirse.setVisible(true);
        inicio.dispose();
    }

    @Override
    public void PasarPantallaFinal() {
        
    }

    @Override
    public void PasarPantallaComoJugar(JDialog children) {
        
    }

    @Override
    public void PasarPantallaSala() {
        
    }

    @Override
    public void PasarPantallaOpciones() {
//        DlgApuesta pantallaApuesta = new DlgApuesta();
//        pantallaApuesta.setVisible(true);
        
    }

    @Override
    public void PasarPantallaInicio() {
       
    }

    @Override
    public void PasarPantallaTablero(ControlPatolli controlPatolli) {
        FrmTablero tablero = new FrmTablero(controlPatolli, "000");
        tablero.setVisible(true);
    }
}
