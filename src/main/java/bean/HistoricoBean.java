package bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import Service.HistoricoService;
import model.entity.Historico;

@Named
@ViewScoped
public class HistoricoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Historico historico;
	
	@Inject
	private HistoricoService service;
	
	public Historico getHistorico() {
		return historico;
	}
	public void setHistorico(Historico historico) {
		this.historico = historico;
	}
	public HistoricoService getService() {
		return service;
	}
	public void setService(HistoricoService service) {
		this.service = service;
	}
	
	

}
