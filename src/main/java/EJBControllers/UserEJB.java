package EJBControllers;

import DTO.UserDTO;
import Entitys.User;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class UserEJB {

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;


    public boolean addUser(String login, String password) {
        System.out.println("addUser in EJB");
        System.out.println("login:" + login + "\npassword" + password);

        if (checkUser(login)) {
            System.out.println("Add User");
            entityManager.persist(new User(login, password));
            return true;
        } else {
            System.out.println("Ass");
            return false;
        }
    }


    public boolean checkUser(String login) {
        Query query = entityManager.createNamedQuery("findByLogin")
                .setParameter("login", login);

        if (query.getResultList().isEmpty()) {
            return true;
        }

        return false;
    }

    public boolean checkPassword(String login, String password) {
        List<UserDTO> user = entityManager.createNamedQuery("findByLogin")
                .setParameter("login", login)
                .getResultList();


        if (user.isEmpty()) {
            System.out.println("User not find");
            return false;
        } else {
            if (user.get(0).getPassword() == password) {
                System.out.println("User find");
                return true;
            } else {
                System.out.println("Password not equals");
                return false;
            }
        }


    }
}
