package EJBControllers;

import Entitys.Note;
import Entitys.User;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class TodoEJB {

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    public User addNote(Integer userId, String title, String description, boolean isPinned) {
        Note note = new Note(title, description, isPinned);
        User user = entityManager.find(User.class, userId);
        note.setUser(user);
        user.getNotes().add(note);
        entityManager.persist(user);

        return user;
    }

    public User editNote(Integer userId, Integer noteId, String title, String description, boolean isPinned) {
        Note note = entityManager.find(Note.class, noteId);
        note.setTitle(title);
        note.setDescription(description);
        note.setPinned(isPinned);
        User user = entityManager.find(User.class, userId);
        user.getNotes().add(note);
        entityManager.persist(user);

        return user;
    }

    public User deleteNote(Integer userId, Integer noteId) {
        Note note = entityManager.find(Note.class, noteId);
        User user = entityManager.find(User.class, userId);
        note.setUser(null);
        user.getNotes().remove(note);
        entityManager.persist(user);

        return user;
    }


}
