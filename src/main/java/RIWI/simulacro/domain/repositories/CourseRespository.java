package RIWI.simulacro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import RIWI.simulacro.domain.entities.Course;


@Repository
public interface CourseRespository extends JpaRepository <Course, Integer> {

}
