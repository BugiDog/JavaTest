package Entitys;

import javax.persistence.*;

@Entity
public class Tag  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private boolean isActive;


}
