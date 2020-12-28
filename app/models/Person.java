package models;

import javax.persistence.*;

@Entity
@Table(name = "tb_person")
public class Person {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;

    public String name;

}
