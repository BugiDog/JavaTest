package EJBControllers;

import Entitys.User;
import Exceptions.AuthException;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class AuthEJB {

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    public User addUser(String login, String password) {
        User user = new User(login, password);
        entityManager.persist(user);
        return user;

    }

    public boolean checkUser(String login) {
        TypedQuery<User> query = entityManager.createNamedQuery("findByLogin", User.class)
                .setParameter("login", login);
        if (query.getResultList().isEmpty()) {
            return true;
        }
        return false;
    }

    public User checkAuthToken(String authToken) throws AuthException {
        TypedQuery<User> query = entityManager.createNamedQuery("findByAuthToken", User.class)
                .setParameter("authToken", authToken);

        try {
            User user = query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            throw new AuthException("User not find");
        }

    }

    public User checkPassword(String login, String password) throws AuthException {
        TypedQuery<User> query = entityManager.createNamedQuery("findByLogin", User.class)
                .setParameter("login", login);

        try {
            User user = query.getSingleResult();
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                throw new AuthException("Invalid Password");
            }
        } catch (NoResultException e) {
            throw new AuthException("User not find");
        }
    }
}
