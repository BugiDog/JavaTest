package Beans;

import EJBControllers.AuthEJB;
import Entitys.User;
import Exceptions.AuthException;

import javax.ejb.EJB;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named
@SessionScoped
public class AuthBean extends Forward implements Serializable {

    private String login;
    private String password;
    private boolean saveUser;
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

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public boolean isSaveUser() {
        return saveUser;
    }

    public void setSaveUser(boolean saveUser) {
        this.saveUser = saveUser;
    }

    public void authCheckCookie() {
//      FacesContext facescontext = FacesContext.getCurrentInstance();
//      ExternalContext externalContext = facescontext.getExternalContext();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        System.out.println("checkAuthToken");
        Map<String, Object> cookies = externalContext.getRequestCookieMap();
        if (cookies.containsKey("authToken")) {
            try {
                Cookie cookie = (Cookie) cookies.get("authToken");
                String authToken = cookie.getValue();
                System.out.println("CheckCookies authToken: " + authToken);
                if (!authToken.isEmpty()) {
                    System.out.println("CheckCookies redirectToPage pages/todoPage.xhtml");
                    User user = authEJB.checkAuthToken(authToken);
                    todoBean.setUser(user);
                    redirectToPage(externalContext, "todoPage.xhtml");
                }
            } catch (AuthException e) {
                System.out.println("CheckCookies redirectToPage authorization.xhtml");
            }
        }
    }

    public void authorization() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        // ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        System.out.println("authorization 2");
        try {
            User user = authEJB.checkPassword(login, password);
            todoBean.setUser(user);
            if (saveUser) {
                Map<String, Object> cookieProperties = new HashMap<>();
//              cookieProperties.put("maxAge", 31536000);// dot work
                externalContext.addResponseCookie("authToken", user.getAuthToken(), cookieProperties);
            }
            System.out.println("AuthBean redirectToPage pages/todoPage.xhtml");

            redirectToPage(externalContext, "todoPage.xhtml");
        } catch (AuthException e) {
            exceptionMessage = e.getMassage();
            addMessage(facesContext, FacesMessage.SEVERITY_ERROR, "", exceptionMessage);
            System.out.println("AuthBean redirectToPage authorization.xhtml");

           // redirectToPage(externalContext, "authorization.xhtml");
        }
    }

    public void goToRegistration() {
        //       FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        System.out.println("AuthBean redirectToPage pages/registration.xhtml");
        redirectToPage(externalContext, "registration.xhtml");
    }


}
