package Beans;

import EJBControllers.AuthEJB;
import EJBControllers.TodoEJB;
import Entitys.Note;
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


    private Integer id;

    private String title;

    private String description;

    private boolean isPinned;

    private boolean modalWindowIsOpen;

    public NoteBean() {
    }

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

    public boolean isModalWindowIsOpen() {
        return modalWindowIsOpen;
    }

    public void setModalWindowIsOpen(boolean modalWindowIsOpen) {
        this.modalWindowIsOpen = modalWindowIsOpen;
    }

    public void openModalWindow() {
        System.out.println("openModalWindow");
        modalWindowIsOpen = true;
    }

    public void openModalWindow(Note note) {
        System.out.println("openModalWindow");
        id = note.getId();
        title = note.getTitle();
        description = note.getDescription();
        isPinned = note.isPinned();
        modalWindowIsOpen = true;
    }

    public void closeModalWindow() {
        System.out.println("closeModalWindow");
        modalWindowIsOpen = false;
    }

    public void deleteNote(Note note) {
        System.out.println(note.toString());
        User user = todoEJB.deleteNote(todoBean.getUser().getId(), note.getId());
        todoBean.setUser(user);
    }


    public void save() {
        User user;
        if (id == null) {
            user = todoEJB.addNote(todoBean.getUser().getId(), title, description, isPinned);
        } else {
            user = todoEJB.editNote(todoBean.getUser().getId(), id, title, description, isPinned);
        }
        id = null;
        title = "";
        description = "";
        isPinned = false;
        closeModalWindow();
        todoBean.setUser(user);
    }

    public void pinNote(Note note) {
        User user = todoEJB.pinNote(todoBean.getUser().getId(), note.getId());
        todoBean.setUser(user);
    }

    public void isOpen(Note note) {
       User user = todoEJB.changeOpenState(todoBean.getUser().getId(),note.getId());
       todoBean.setUser(user);
    }

    public void send(String str) {
        System.out.println(str);
    }


}
