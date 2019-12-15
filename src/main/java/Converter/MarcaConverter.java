package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import Service.MarcaService;
import model.entity.Marca;
import model.entity.Tipo;

@FacesConverter("marcaConverter")
public class MarcaConverter implements Converter{
	
	@Inject
	private MarcaService marcaService;
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		Marca marca;
		marca = null;
		try {
			Integer idMarca = Integer.parseInt(string);
			marca = marcaService.buscaPorId(idMarca);
		}catch(Exception e) {
			
		}
		return marca;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		Marca marca = (Marca) o;
        return marca.getIdMarca()+ "";
	}

}
