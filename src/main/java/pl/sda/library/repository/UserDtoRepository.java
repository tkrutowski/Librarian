package pl.sda.library.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface UserDtoRepository extends CrudRepository<UserDto, Long> {

     Optional<UserDto> findUserDtoByLogin(String login);

}
