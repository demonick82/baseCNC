package stp.demonick.basecncprog.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "models3D")
public class Model3D {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String path;

}
