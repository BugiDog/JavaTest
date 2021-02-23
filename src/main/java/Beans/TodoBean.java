package Beans;

import EJBControllers.AuthEJB;
import Entitys.User;
import Exceptions.AuthException;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named
@SessionScoped
public class TodoBean extends Forward implements Serializable {

    private User user;

    @EJB
    private AuthEJB authEJB;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void todoCheckCookie() {
//      FacesContext facescontext = FacesContext.getCurrentInstance();
//      ExternalContext externalContext = facescontext.getExternalContext();
        if (user == null){
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
                        user = authEJB.checkAuthToken(authToken);
                    } else {
                        redirectToPage(externalContext, "authorization.xhtml");
                    }
                } catch (AuthException e) {
                    System.out.println("CheckCookies redirectToPage authorization.xhtml");
                    redirectToPage(externalContext, "authorization.xhtml");
                }
            } else {
                redirectToPage(externalContext, "authorization.xhtml");
            }
        }

    }

    public void exit(){
        System.out.println("exit");
//        FacesContext facescontext = FacesContext.getCurrentInstance();
//        ExternalContext externalContext = facescontext.getExternalContext();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        user= null;
        Map<String,Object> cookieProperties = new HashMap<String, Object>();
        cookieProperties.put("maxAge", 0);
        externalContext.addResponseCookie("authToken", "", cookieProperties);
        System.out.println("TodoBean redirectToPage authorization.xhtml");
        redirectToPage(externalContext, "authorization.xhtml");
    }

    public void addNote(){


    }

}
