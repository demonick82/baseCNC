package stp.demonick.basecncprog.service;

import org.springframework.stereotype.Service;
import stp.demonick.basecncprog.model.Machine;
import stp.demonick.basecncprog.repository.MachineRepository;

@Service
public class MachineService {
    private final MachineRepository machineRepository;

    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    Machine findMachineByNXName(String name) {
        return machineRepository.findByNameForNX(name);
    }
}
