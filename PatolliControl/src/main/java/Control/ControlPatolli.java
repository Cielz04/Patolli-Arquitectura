package Control;

import entidades.Juego;
import entidades.Tablero;
import java.awt.Color;

/**
 *
 * @author Hector Espinoza
 */
public class ControlPatolli {
    
    static ControlPatolli  controlSingleTon;
   
    
    
  public void configurarJugadores(int canJugadores){
      Juego.getInstance().setCanJugadores(4);
      
      Juego.getInstance().addJugador();
  } 
  
  public void setCantidadCasillas (int casillas){
      Juego.getInstance().setNumCasillasAspa(casillas);  
  }
  
  public void setCantidadFichas(int canFichas){
      Juego.getInstance().setCantFichas(canFichas);
  }
  
  public void setApuesta (int apuesta){
      Juego.getInstance().setApuesta(apuesta);
  }
  
  public int getCasillasAspas(){
      return Juego.getInstance().getNumCasillasAspa();
  }
  
  /**
   * MODIFICAR
   * NO SE DEBE TENER ACCESSO DIRECTO AL TABLERO, SOLO A JUEGO
   * @param tablero 
   */
  public void setTablero (Tablero tablero){
      Juego.getInstance().setTablero(tablero);
  }
  
  public Color getColorTurnoJugador(){
      return Juego.getInstance().getJugadores().get(1).getColor();
  }
  
  public Tablero getTablero(){
      return Juego.getInstance().getTablero();
  }
  
  public static ControlPatolli getInstance(){
      if (controlSingleTon==null){
          controlSingleTon = new ControlPatolli();
      }
      return controlSingleTon;
  }
    
}
