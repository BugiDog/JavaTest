package Entitys;


import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "deleteById",
                //query = "DELETE c FROM Note c WHERE c.id=id"
                query = "DELETE FROM Note n WHERE n.id = :id"
        ),

})

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private boolean isPinned;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Note() {
    }

    public Note( String title, String description, boolean isPinned) {
        this.title = title;
        this.description = description;
        this.isPinned = isPinned;
    }

    public Note(Integer id, String title, String description, boolean isPinned) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isPinned = isPinned;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isPinned=" + isPinned +
                '}';
    }
}
