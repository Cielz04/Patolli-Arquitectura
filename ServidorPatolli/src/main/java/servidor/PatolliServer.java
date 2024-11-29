/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

/**
 *
 * @author Hector Espinoza
 */
public class PatolliServer implements IPatolliServer{
    
    Servidor servidor;
    
    public PatolliServer() {
        servidor = new Servidor();
    }

    
    
    @Override
    public void initServidor() {
        servidor.init();
    }
    
    
    
}
