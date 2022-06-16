package stp.demonick.basecncprog.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "operation_blanks")
public class OperationBlank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String path;
    private String comment;
}
