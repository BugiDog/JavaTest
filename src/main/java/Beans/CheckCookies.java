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
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;


@Named
@SessionScoped
public class CheckCookies implements Serializable {

    @Inject
    TodoBean todoBean;

    @EJB
    private AuthEJB authEJB;

    public void checkAuthToken() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> cookies = externalContext.getRequestCookieMap();
        if (cookies.containsKey("authToken")) {
            try {
                Cookie cookie = (Cookie) cookies.get("authToken");
                String authToken = cookie.getValue();
                User user = authEJB.checkAuthToken(authToken);
                todoBean.setUser(user);
                redirectToPage(externalContext, "pages/todoPage.xhtml");
            } catch (AuthException e) {
                redirectToPage(externalContext, "authorization.xhtml");
            }
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
