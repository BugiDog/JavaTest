package Beans;

import EJBControllers.UserEJB;

import javax.ejb.EJB;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class AuthBean implements Serializable {

    private String login;
    private String password;

    private boolean loginSuccess;


    @EJB
    private UserEJB userEJB;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addUser(){
       boolean chek = userEJB.addUser(login, password);
        System.out.println("chek:"+chek);
        try {
            if (chek){
                FacesContext.getCurrentInstance().getExternalContext().redirect("pages/todoPage.xhtml");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("authorization.xhtml");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
