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
    private String path;
    private String comment;

    public OperationBlank(String path, String comment) {
        this.path = path;
        this.comment = comment;
    }

    public OperationBlank() {

    }
}
