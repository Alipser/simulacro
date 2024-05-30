package RIWI.simulacro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import RIWI.simulacro.domain.entities.Submission;


@Repository
public interface SubmissionRespository extends JpaRepository <Submission, Integer> {

}
