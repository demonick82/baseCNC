package stp.demonick.basecncprog.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "machines")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String machineName;

    public void setMachineName(String machineName) {
        this.machineName = machineName.replace("_"," ");
    }
}
