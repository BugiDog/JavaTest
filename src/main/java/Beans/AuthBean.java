package Beans;

import EJBControllers.AuthEJB;
import Entitys.User;
import Exceptions.AuthException;

import javax.ejb.EJB;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named
@SessionScoped
public class AuthBean implements Serializable {

    private String login;
    private String password;
    private boolean saveUser = false;
    private String ExceptionMessage;

    @Inject
    TodoBean todoBean;

    @EJB
    private AuthEJB authEJB;

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

    public String getExceptionMessage() {
        return ExceptionMessage;
    }

    public boolean isSaveUser() {
        return saveUser;
    }

    public void setSaveUser(boolean saveUser) {
        this.saveUser = saveUser;
    }

    public void authorization() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            User user = authEJB.checkPassword(login, password);
            todoBean.setUser(user);
            if (saveUser) {
                Map<String, Object> cookieProperties = new HashMap<>();
//              cookieProperties.put("maxAge", 31536000);// dot work
                externalContext.addResponseCookie("authToken", user.getAuthToken(), cookieProperties);
            }
            redirectToPage(externalContext, "pages/todoPage.xhtml");
        } catch (AuthException e) {
            ExceptionMessage = e.getMassage();
            redirectToPage(externalContext, "authorization.xhtml");
        }
    }

    private void redirectToPage(ExternalContext externalContext, String URL) {
        try {
            externalContext.redirect(URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
