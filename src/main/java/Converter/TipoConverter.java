package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import Service.TipoService;
import model.entity.Tipo;

@FacesConverter("tipoConverter")
public class TipoConverter implements Converter{
	
	@Inject
	private TipoService tipoService;
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		Tipo tipo;
		tipo = null;
		try {
			Integer idTipo = Integer.parseInt(string);
			tipo = tipoService.buscaPorId(idTipo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tipo;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {

		Tipo tipo = (Tipo) o;
        return tipo.getIdTipo()+ "";
	}

}
