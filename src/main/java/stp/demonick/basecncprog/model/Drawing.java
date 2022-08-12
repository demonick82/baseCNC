package stp.demonick.basecncprog.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "drawings")
public class Drawing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String path;

    public Drawing(String path) {
        this.path = path;
    }

    public Drawing() {
    }
}
