package RIWI.simulacro.domain.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import RIWI.simulacro.domain.entities.Assignment;



@Repository
public interface AssignmentRespository extends JpaRepository <Assignment, Integer> {

}
