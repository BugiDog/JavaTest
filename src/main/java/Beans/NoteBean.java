package Beans;

import EJBControllers.AuthEJB;
import EJBControllers.TodoEJB;
import Entitys.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class NoteBean implements Serializable {

    @EJB
    private TodoEJB todoEJB;

    @Inject
    TodoBean todoBean;


    private String title;

    private String description;

    private boolean isPinned;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    public void addNote(){
       User user = todoEJB.addNote(todoBean.getUser().getId(),title,description,isPinned);
       todoBean.setUser(user);
    }


}
