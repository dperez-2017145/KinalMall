
package org.danielperez.system;

import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.danielperez.controller.AdministracionController;
import org.danielperez.controller.CargoController;
import org.danielperez.controller.ClienteController;
import org.danielperez.controller.CuentaPorCobrarController;
import org.danielperez.controller.CuentaPorPagarController;
import org.danielperez.controller.DepartamentoController;
import org.danielperez.controller.EmpleadoController;
import org.danielperez.controller.HorarioController;
import org.danielperez.controller.LocalController;
import org.danielperez.controller.LoginController;
import org.danielperez.controller.MenuPrincipalController;
import org.danielperez.controller.ProgramadorController;
import org.danielperez.controller.ProveedorController;
import org.danielperez.controller.TipoClienteController;
import org.danielperez.controller.UsuarioController;


public class Principal extends Application {
    private final String PAQUETE_VISTA = "/org/danielperez/view/"; //Ruta de las vistas, es una constante ya que nunca va a cambiar.
    private Stage escenarioPrincipal;
    private Scene escena;
    @Override
    public void start(Stage escenarioPrincipal) throws IOException {
        this.escenarioPrincipal = escenarioPrincipal;
        this.escenarioPrincipal.setTitle("KinalMall 2017145");
        this.escenarioPrincipal.getIcons().add(new Image("/org/danielperez/images/KinalMallIcon.png")); 

          ventanaLogin(); //ventanaUsuario en lugar de menuPrincipal;
          escenarioPrincipal.show();
          
    }
    
    public void menuPrincipal(){
       try{
           MenuPrincipalController menuPrincipal = (MenuPrincipalController)cambiarEscena ("MenuPrincipalView.fxml", 582, 394);
           menuPrincipal.setEscenarioPrincipal(this);
       }catch(Exception e){
            e.printStackTrace();
       } 
    }
    
    public void ventanaProgramador(){
        try{
            ProgramadorController programador = (ProgramadorController)cambiarEscena ("ProgramadorView.fxml", 600, 398);
            programador.setEscenarioPrincipal(this);
       }catch(Exception e){
            e.printStackTrace();
       }
    
    
    }
    
    public void ventanaAdministracion(){
        try{
            AdministracionController admin = (AdministracionController)cambiarEscena ("AdministracionView.fxml", 882, 430);
            admin.setEscenarioPrincipal(this);
    
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }
    
    public void ventanaTipoCliente(){
        try{
            TipoClienteController tipoCliente = (TipoClienteController)cambiarEscena ("TipoClienteView.fxml", 900, 430);
            tipoCliente.setEscenarioPrincipal(this); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaLocales(){
        try{
            LocalController locales = (LocalController) cambiarEscena ("LocalesView.fxml", 1205, 430);
            locales.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaDepartamento(){
        try{
            DepartamentoController departamento = (DepartamentoController) cambiarEscena ("DepartamentosView.fxml", 882, 430);
            departamento.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaCliente(){
        try{
            ClienteController cliente = (ClienteController) cambiarEscena ("ClienteView.fxml", 1425, 445);
            cliente.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaProveedor(){
        try{
            ProveedorController proveedor = (ProveedorController) cambiarEscena ("ProveedorView.fxml", 1275, 454);
            proveedor.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }
    
    public void ventanaCuentasPorPagar(){
        try{
            CuentaPorPagarController cuentas = (CuentaPorPagarController) cambiarEscena ("CuentaPorPagarView.fxml", 1275, 455);
            cuentas.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaHorarios(){
        try{
            HorarioController horario = (HorarioController) cambiarEscena ("HorarioView.fxml", 1121, 455);
            horario.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }
    
    public void ventanaCargo(){
        try{
            CargoController cargo = (CargoController) cambiarEscena ("CargoView.fxml", 900, 430);
            cargo.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaEmpleado(){
        try{
            EmpleadoController empleado = (EmpleadoController) cambiarEscena ("EmpleadoView.fxml", 1700, 500);
            empleado.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaCuentaPorCobrar(){
        try{
            CuentaPorCobrarController cuentapc = (CuentaPorCobrarController) cambiarEscena ("CuentaPorCobrarView.fxml", 1325, 455);
            cuentapc.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaUsuario(){
        try{
            UsuarioController user = (UsuarioController) cambiarEscena ("UsuarioView.fxml", 750, 400);
            user.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaLogin(){
        try{
            LoginController login = (LoginController) cambiarEscena ("LoginView.fxml", 600, 400);
            login.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public Initializable cambiarEscena(String fxml, int ancho, int alto) throws IOException{
        Initializable resultado = null;
        FXMLLoader cargadorFXML = new FXMLLoader();
        InputStream archivo = Principal.class.getResourceAsStream(PAQUETE_VISTA + fxml);
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
        cargadorFXML.setLocation(Principal.class.getResource(PAQUETE_VISTA +fxml));
        escena = new Scene((AnchorPane)cargadorFXML.load(archivo), ancho, alto);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.sizeToScene();
        resultado = (Initializable)cargadorFXML.getController();
        return resultado;
    } 

    
    
}

  