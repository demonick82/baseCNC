package stp.demonick.basecncprog.service;

import org.springframework.stereotype.Service;
import stp.demonick.basecncprog.model.Programmer;
import stp.demonick.basecncprog.repository.ProgrammerRepository;

@Service
public class ProgrammerService {
  private final   ProgrammerRepository programmerRepository;

    public ProgrammerService(ProgrammerRepository programmerRepository) {
        this.programmerRepository = programmerRepository;
    }

    public Programmer findByLogin(String login) {
        return programmerRepository.findByLogin(login);
    }
}
