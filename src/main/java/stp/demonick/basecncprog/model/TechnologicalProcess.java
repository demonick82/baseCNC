package stp.demonick.basecncprog.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "technological_processes")
public class TechnologicalProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String path;
}
