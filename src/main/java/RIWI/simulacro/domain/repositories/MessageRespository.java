package RIWI.simulacro.domain.repositories;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import RIWI.simulacro.domain.entities.Message;
import RIWI.simulacro.domain.entities.User;


@Repository
public interface MessageRespository extends JpaRepository <Message, Integer> {

    public List<Message> findBySenderAndReciver(User sender, User reciver);

}
