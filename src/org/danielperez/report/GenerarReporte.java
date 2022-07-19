package org.danielperez.report;

import java.io.InputStream;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.danielperez.db.Conexion;


public class GenerarReporte {
    public static void mostrarReporte(String nombreReporte, String tituloReporte, Map parametros){
            InputStream reporte = GenerarReporte.class.getResourceAsStream(nombreReporte);
            try{
                JasperReport reporteMaestro = (JasperReport)JRLoader.loadObject(reporte);
                JasperPrint reporteImpreso = JasperFillManager.fillReport(reporteMaestro, parametros, Conexion.getInstance().getConexion());
                JasperViewer visor = new JasperViewer(reporteImpreso, false);
                visor.setTitle(tituloReporte);
                visor.setVisible(true);
            }catch(Exception e){
                e.printStackTrace();
            }
    }
}
