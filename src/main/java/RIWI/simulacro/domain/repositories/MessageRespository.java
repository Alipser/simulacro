package RIWI.simulacro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import RIWI.simulacro.domain.entities.Message;


@Repository
public interface MessageRespository extends JpaRepository <Message, Integer> {

}
