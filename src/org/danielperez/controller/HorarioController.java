package org.danielperez.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.danielperez.bean.Horario;
import org.danielperez.db.Conexion;
import org.danielperez.system.Principal;


public class HorarioController implements Initializable {
    private enum operaciones{NUEVO, GUARDAR, ELIMINAR, ACTUALIZAR, CANCELAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private Principal escenarioPrincipal;
    private ObservableList<Horario> listaHorario;
    @FXML private Button btnNuevo;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;
    @FXML private Button btnReporte;
    @FXML private TextField txtCodigoHorario;
    @FXML private TextField txtHoraEntrada;
    @FXML private TextField txtHoraSalida;
    @FXML private TextField txtLunes;
    @FXML private TextField txtMartes;
    @FXML private TextField txtMiercoles;
    @FXML private TextField txtJueves;
    @FXML private TextField txtViernes;
    @FXML private TableView tblHorarios;
    @FXML private TableColumn colCodigoHorario;
    @FXML private TableColumn colHoraEntrada;
    @FXML private TableColumn colHoraSalida;
    @FXML private TableColumn colLunes;
    @FXML private TableColumn colMartes;
    @FXML private TableColumn colMiercoles;
    @FXML private TableColumn colJueves;
    @FXML private TableColumn colViernes;
    @FXML private ImageView imgNuevo;
    @FXML private ImageView imgEliminar;
    @FXML private ImageView imgEditar;
    @FXML private ImageView imgReporte;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }
    
    public void cargarDatos(){
        tblHorarios.setItems(getHorario());
        colCodigoHorario.setCellValueFactory(new PropertyValueFactory<Horario,Integer>("codigoHorario"));
        colHoraEntrada.setCellValueFactory(new PropertyValueFactory<Horario,String>("horarioEntrada"));
        colHoraSalida.setCellValueFactory(new PropertyValueFactory<Horario,String>("horarioSalida"));
        colLunes.setCellValueFactory(new PropertyValueFactory<Horario,Boolean>("lunes"));
        colMartes.setCellValueFactory(new PropertyValueFactory<Horario,Boolean>("martes"));
        colMiercoles.setCellValueFactory(new PropertyValueFactory<Horario,Boolean>("miercoles"));
        colJueves.setCellValueFactory(new PropertyValueFactory<Horario,Boolean>("jueves"));
        colViernes.setCellValueFactory(new PropertyValueFactory<Horario,Boolean>("viernes"));
    }
    
    public void seleccionarElemento(){
        if(tblHorarios.getSelectionModel().getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
        }else{
            txtCodigoHorario.setText(String.valueOf(((Horario)tblHorarios.getSelectionModel().getSelectedItem()).getCodigoHorario()));
            txtHoraEntrada.setText(((Horario)tblHorarios.getSelectionModel().getSelectedItem()).getHorarioEntrada());
            txtHoraSalida.setText(((Horario)tblHorarios.getSelectionModel().getSelectedItem()).getHorarioSalida());
            txtLunes.setText(String.valueOf(((Horario)tblHorarios.getSelectionModel().getSelectedItem()).isLunes()));
            txtMartes.setText(String.valueOf(((Horario)tblHorarios.getSelectionModel().getSelectedItem()).isMartes()));
            txtMiercoles.setText(String.valueOf(((Horario)tblHorarios.getSelectionModel().getSelectedItem()).isMiercoles()));
            txtJueves.setText(String.valueOf(((Horario)tblHorarios.getSelectionModel().getSelectedItem()).isJueves()));
            txtViernes.setText(String.valueOf(((Horario)tblHorarios.getSelectionModel().getSelectedItem()).isViernes()));
        }
    
    }
    
    public ObservableList<Horario> getHorario(){
        ArrayList<Horario> lista = new ArrayList<Horario>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{Call sp_ListarHorario()}");
            ResultSet resultado = procedimiento.executeQuery();
            while(resultado.next()){
                lista.add(new Horario(resultado.getInt("codigoHorario"),
                                        resultado.getString("horarioEntrada"),
                                        resultado.getString("horarioSalida"),
                                        resultado.getBoolean("lunes"),
                                        resultado.getBoolean("martes"),
                                        resultado.getBoolean("miercoles"),
                                        resultado.getBoolean("jueves"),
                                        resultado.getBoolean("viernes")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaHorario = FXCollections.observableArrayList(lista);
    }
    
    public void nuevo(){
        switch(tipoDeOperacion){
            case NINGUNO:
                activarControles();
                limpiarControles();
                btnNuevo.setText("Guardar");
                btnEliminar.setText("Cancelar");
                btnEditar.setDisable(true);
                btnReporte.setDisable(true);
                imgNuevo.setImage(new Image("/org/danielperez/images/Guardar.png"));
                imgEliminar.setImage(new Image("/org/danielperez/images/Cancelar .png"));
                tipoDeOperacion = operaciones.GUARDAR;
                break;
            case GUARDAR:
                guardar();
                desactivarControles();
                limpiarControles();
                btnNuevo.setText("Nuevo");
                btnEliminar.setText("Eliminar");
                btnEditar.setDisable(false);
                btnReporte.setDisable(false);
                imgNuevo.setImage(new Image("/org/danielperez/images/Nuevo.png"));
                imgEliminar.setImage(new Image("/org/danielperez/images/Eliminar.png"));
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
        }
    }
    
    public void eliminar(){
        switch(tipoDeOperacion){
            case GUARDAR:
                desactivarControles();
                limpiarControles();
                btnNuevo.setText("Nuevo");
                btnEliminar.setText("Eliminar");
                imgNuevo.setImage(new Image("/org/danielperez/images/Nuevo.png"));
                imgEliminar.setImage(new Image("/org/danielperez/images/Eliminar.png"));
                btnEditar.setDisable(false);
                btnReporte.setDisable(false);
                tipoDeOperacion = operaciones.NINGUNO;
                break;
            default:
                if(tblHorarios.getSelectionModel().getSelectedItem() != null){
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que quiere eliminar el registro?", "Eliminar Administracion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if(respuesta == JOptionPane.YES_OPTION){
                    try{
                        PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{Call sp_EliminarHorario(?)}");
                        procedimiento.setInt(1, ((Horario)tblHorarios.getSelectionModel().getSelectedItem()).getCodigoHorario());
                        procedimiento.execute();
                        listaHorario.remove(tblHorarios.getSelectionModel().getSelectedIndex());
                        limpiarControles();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
                }
        
        }
    
    }
    
    public void editar(){
    switch(tipoDeOperacion){
            case NINGUNO:
                if (tblHorarios.getSelectionModel().getSelectedItem() != null){
                    btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    imgEditar.setImage(new Image("/org/danielperez/images/Actualizar .png"));
                    imgReporte.setImage(new Image("/org/danielperez/images/Cancelar .png"));
                    btnNuevo.setDisable(true);
                    btnEliminar.setDisable(true);
                    activarControles();
                    tipoDeOperacion = operaciones.ACTUALIZAR;
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
                }
                break;
            case ACTUALIZAR:
                actualizar();
                btnEditar.setText("Editar");
                btnReporte.setText("Reporte");
                imgEditar.setImage(new Image("/org/danielperez/images/Editar.png"));
                imgReporte.setImage(new Image("/org/danielperez/images/Reporte.png"));
                btnNuevo.setDisable(false);
                btnEliminar.setDisable(false);
                desactivarControles();
                limpiarControles();
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
                break;
    }
    }
    
    public void reporte(){
        switch(tipoDeOperacion){
            case ACTUALIZAR:
                btnEditar.setText("Editar");
                btnReporte.setText("Reporte");
                imgEditar.setImage(new Image("/org/danielperez/images/Editar.png"));
                imgReporte.setImage(new Image("/org/danielperez/images/Reporte.png"));
                btnNuevo.setDisable(false);
                btnEliminar.setDisable(false);
                desactivarControles();
                limpiarControles();
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
                break; 
        }
    }
    
    public void actualizar(){
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{Call sp_EditarHorario(?,?,?,?,?,?,?,?)}");
            Horario registro = (Horario)tblHorarios.getSelectionModel().getSelectedItem();
            registro.setHorarioEntrada(txtHoraEntrada.getText());
            registro.setHorarioSalida(txtHoraSalida.getText());
            registro.setLunes(Boolean.parseBoolean(txtLunes.getText()));
            registro.setMartes(Boolean.parseBoolean(txtMartes.getText()));
            registro.setMiercoles(Boolean.parseBoolean(txtMiercoles.getText()));
            registro.setJueves(Boolean.parseBoolean(txtJueves.getText()));
            registro.setViernes(Boolean.parseBoolean(txtViernes.getText()));
            procedimiento.setInt(1, registro.getCodigoHorario());
            procedimiento.setString(2, registro.getHorarioEntrada());
            procedimiento.setString(3, registro.getHorarioSalida());
            procedimiento.setBoolean(4, registro.isLunes());
            procedimiento.setBoolean(5, registro.isMartes());
            procedimiento.setBoolean(6, registro.isMiercoles());
            procedimiento.setBoolean(7, registro.isJueves());
            procedimiento.setBoolean(8, registro.isViernes());
            procedimiento.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }
    
    public void guardar(){
        Horario registro = new Horario();
        registro.setHorarioEntrada(txtHoraEntrada.getText());
        registro.setHorarioSalida(txtHoraSalida.getText());
        registro.setLunes(Boolean.parseBoolean(txtLunes.getText()));
        registro.setMartes(Boolean.parseBoolean(txtMartes.getText()));
        registro.setMiercoles(Boolean.parseBoolean(txtMiercoles.getText()));
        registro.setJueves(Boolean.parseBoolean(txtJueves.getText()));
        registro.setViernes(Boolean.parseBoolean(txtViernes.getText()));
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{Call sp_AgregarHorario(?,?,?,?,?,?,?)}");
            procedimiento.setString(1, registro.getHorarioEntrada());
            procedimiento.setString(2, registro.getHorarioSalida());
            procedimiento.setBoolean(3, registro.isLunes());
            procedimiento.setBoolean(4, registro.isMartes());
            procedimiento.setBoolean(5, registro.isMiercoles());
            procedimiento.setBoolean(6, registro.isJueves());
            procedimiento.setBoolean(7, registro.isViernes());
            procedimiento.execute();
            listaHorario.add(registro);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void desactivarControles(){
        txtCodigoHorario.setEditable(false);
        txtHoraEntrada.setEditable(false);
        txtHoraSalida.setEditable(false);
        txtLunes.setEditable(false);
        txtMartes.setEditable(false);
        txtMiercoles.setEditable(false);
        txtJueves.setEditable(false);
        txtViernes.setEditable(false);
    }
    
    public void activarControles(){
        txtCodigoHorario.setEditable(false);
        txtHoraEntrada.setEditable(true);
        txtHoraSalida.setEditable(true);
        txtLunes.setEditable(true);
        txtMartes.setEditable(true);
        txtMiercoles.setEditable(true);
        txtJueves.setEditable(true);
        txtViernes.setEditable(true);
    }
    
    public void limpiarControles(){
        txtCodigoHorario.clear();
        txtHoraEntrada.clear();
        txtHoraSalida.clear();
        txtLunes.clear();
        txtMartes.clear();
        txtMiercoles.clear();
        txtJueves.clear();
        txtViernes.clear();
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void menuPrincipal(){
        escenarioPrincipal.menuPrincipal();
    }
    
    
}
