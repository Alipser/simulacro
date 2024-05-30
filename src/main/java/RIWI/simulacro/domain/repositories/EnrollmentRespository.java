package RIWI.simulacro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import RIWI.simulacro.domain.entities.Enrollment;


@Repository
public interface EnrollmentRespository extends JpaRepository <Enrollment, Integer> {

}
