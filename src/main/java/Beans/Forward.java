package Beans;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

public class Forward {

//    protected void redirectToPage(FacesContext facescontext, String URL){
//        facescontext.getApplication().getNavigationHandler().handleNavigation(facescontext,null,URL);
//
//    }

    protected void redirectToPage(ExternalContext externalContext, String URL){
        try {
            externalContext.redirect(URL);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
