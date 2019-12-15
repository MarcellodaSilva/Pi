package bean;


import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import Service.MarcaService;
import Service.TipoService;
import model.entity.Marca;
import model.entity.Tipo;

@Named
@RequestScoped
public class CadMarcaTipoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Marca marca;
	
	@Inject
	private MarcaService marcaService;
	
	@Inject
	private Tipo tipo;
	
	@Inject
	private TipoService tipoService;

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public MarcaService getMarcaService() {
		return marcaService;
	}

	public void setMarcaService(MarcaService marcaService) {
		this.marcaService = marcaService;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public TipoService getTipoService() {
		return tipoService;
	}
	public void setTipoService(TipoService tipoService) {
		this.tipoService = tipoService;
	}
	
	public void adicionar() throws Exception {
		marcaService.cadastrarMarca(marca);
		tipoService.cadastrarTipo(tipo);
	}
}
