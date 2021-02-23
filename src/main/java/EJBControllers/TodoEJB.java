package EJBControllers;

import Entitys.Note;
import Entitys.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TodoEJB {

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    public User addNote(Integer userId, String title, String description, boolean isPinned){

        Note note = new Note(title, description, isPinned);
        User user = entityManager.find(User.class, userId);
        note.setUser(user);
        user.getNotes().add(note);

        entityManager.persist(user);
         return user;
    }


}
