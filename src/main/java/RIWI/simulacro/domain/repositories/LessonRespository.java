package RIWI.simulacro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import RIWI.simulacro.domain.entities.Lesson;


@Repository
public interface LessonRespository extends JpaRepository <Lesson, Integer> {

}
