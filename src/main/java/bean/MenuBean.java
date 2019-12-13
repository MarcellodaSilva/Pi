package bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

import model.entity.Cliente;
import model.entity.Farmacia;

@Named
@ViewScoped
public class MenuBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private DefaultMenuModel menuModel;
	
	public DefaultMenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(DefaultMenuModel menuModel) {
		this.menuModel = menuModel;
	}

	@PostConstruct
	public void init() {
		menuModel = new DefaultMenuModel();
		DefaultSubMenu subMenu;
		DefaultMenuItem item = new DefaultMenuItem();
		Object user = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Perfil");

		if (user instanceof Cliente) {
			Cliente cliente = (Cliente) user;
			subMenu = new DefaultSubMenu(cliente.getNome());
			item = new DefaultMenuItem();
			item.setValue("Editar Dados");
			item.setUrl("editar_cliente.xhtml");
			subMenu.addElement(item);

			item = new DefaultMenuItem();
			item.setValue("Excluir Conta");
			item.setUrl("excluir.xhtml");
			subMenu.addElement(item);
			menuModel.addElement(subMenu);

		} else if (user instanceof Farmacia) {
			Farmacia farmacia = (Farmacia) user;
			subMenu= new DefaultSubMenu(farmacia.getNome());
			item = new DefaultMenuItem();
			item.setValue("Editar Dados");
			item.setUrl("editar_farmacia.xhtml");
			menuModel.addElement(item);

			item = new DefaultMenuItem();
			item.setValue("Gerenciar Estoque");
			item.setUrl("listar.xhtml");
			menuModel.addElement(item);

			item = new DefaultMenuItem();
			item.setValue("Excluir Conta");
			item.setUrl("excluir.xhtml");
			menuModel.addElement(item);
			menuModel.addElement(subMenu);
		}

	}
}
