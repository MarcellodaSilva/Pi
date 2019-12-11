package utils;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.tree.TreeNode;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import Service.ProdutoService;

@Named("ttEditView")
@ViewScoped
public class Listar implements Serializable{
     
    private TreeNode root;
     

     
    @Inject
    private ProdutoService service;
     
 
    public TreeNode getRoot() {
        return root = (TreeNode) service.listarProdutos();
    }
 
     
    public void setService(ProdutoService service) {
        this.service = service;
    }
     
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Document Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    } 
}
