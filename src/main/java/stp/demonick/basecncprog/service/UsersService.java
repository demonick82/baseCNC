package stp.demonick.basecncprog.service;

import org.springframework.stereotype.Service;
import stp.demonick.basecncprog.exceptions.NotFoundException;
import stp.demonick.basecncprog.model.User;
import stp.demonick.basecncprog.repository.UsersRepository;

import java.util.Optional;

@Service
public class UsersService {
  private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User findByLogin(String login) {
        Optional<User> dateBaseUser = Optional.ofNullable(usersRepository.findByLogin(login));
        return dateBaseUser.orElseThrow(
                () -> new NotFoundException("User not found"));
    }
}
