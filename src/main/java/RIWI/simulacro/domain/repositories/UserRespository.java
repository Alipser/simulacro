package RIWI.simulacro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import RIWI.simulacro.domain.entities.User;

@Repository
public interface UserRespository extends JpaRepository <User, Integer> {

}
