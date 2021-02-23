package Beans;


import EJBControllers.AuthEJB;
import Entitys.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named
@SessionScoped
public class RegistrationBean extends Forward implements Serializable {

    private String login;
    private String password;
    private String secondPassword;
    private boolean saveUser = false;
    private String exceptionMessage;

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

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) {
        this.secondPassword = secondPassword;
    }

    public boolean isSaveUser() {
        return saveUser;
    }

    public void setSaveUser(boolean saveUser) {
        this.saveUser = saveUser;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void registration() {
//        FacesContext facescontext = FacesContext.getCurrentInstance();
//        ExternalContext externalContext = facescontext.getExternalContext();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        if (password.equals(secondPassword)) {
            if (authEJB.checkUser(login)) {
                User user = authEJB.addUser(login, password);
                todoBean.setUser(user);
                if (saveUser) {
                    Map<String, Object> cookieProperties = new HashMap<>();
//              cookieProperties.put("maxAge", 31536000);// dot work
                    externalContext.addResponseCookie("authToken", user.getAuthToken(), cookieProperties);
                }
                System.out.println("registration redirectToPage pages/todoPage.xhtml");
                redirectToPage(externalContext, "todoPage.xhtml");
            }else {
                exceptionMessage = "login is busy";
                System.out.println("registration redirectToPage pages/todoPage.xhtml");
                redirectToPage(externalContext, "registration.xhtml");
            }
        } else {
            exceptionMessage = "passwords don't match ";
            System.out.println("registration redirectToPage pages/todoPage.xhtml");
            redirectToPage(externalContext, "registration.xhtml");
        }

    }

}
