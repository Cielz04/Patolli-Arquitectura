
package Control;

import Pantallas.FrmInicio;
import javax.swing.JDialog;

/**
 *
 * @author esmeraldamolinaestrada
 */
public interface IControlPantalla {
    void PasarPantallaUnirseCrear(FrmInicio inicio);

    void PasarPantallaFinal();

    void PasarPantallaComoJugar(JDialog children);

    void PasarPantallaSala();

    void PasarPantallaOpciones();

    void PasarPantallaInicio();

    void PasarPantallaTablero(ControlPatolli controlPatolli);
}
