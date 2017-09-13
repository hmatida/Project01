package br.com.etqpadrao.etqpadrao.relatorioClasses;

import br.com.etqpadrao.etqpadrao.models.EtiquetaProduto;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EtiquetaBomPeixe {

    private HttpServletResponse response;
    private FacesContext context;
    private ByteArrayOutputStream baos;
    private InputStream stream;

    public EtiquetaBomPeixe(){
        this.context=FacesContext.getCurrentInstance();
        this.response= (HttpServletResponse) context.getExternalContext().getResponse();
    }

    public void  getRelatorio(List<EtiquetaProduto> etiquetaProdutoList){
        stream = this.getClass().getResourceAsStream("/report/etiqueta_bompeixe.jasper");
        Map<String, Object> params = new HashMap<String, Object>();
        baos = new ByteArrayOutputStream();

        try {
            JasperReport report = (JasperReport) JRLoader.loadObject(stream);
            JasperPrint print = JasperFillManager.fillReport(report, params,
                    new JRBeanCollectionDataSource(etiquetaProdutoList));
            JasperExportManager.exportReportToPdfStream(print, baos);
            response.reset();
            response.setContentType("aplication/pdf");
            response.setContentLength(baos.size());
            response.setHeader("Content-disposition", "inline; filername=relatorio.pdf");
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();

            context.responseComplete();

        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
