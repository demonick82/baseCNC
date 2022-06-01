package stp.demonick.basecncprog.model;

public class Machine {
    private int id;
    private String name;

    public static Machine of(String name) {
        Machine machine = new Machine();
        machine.name = name;
        return machine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
