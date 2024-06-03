package RIWI.simulacro.domain.entities;



import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false, length = 100 )
    private String lessonTitle;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String content;
    
    @ManyToOne
    @JoinColumn(name="course_id", referencedColumnName = "id")
    private Course course;

    @OneToMany(mappedBy ="lesson")
    private List<Assignment> assignments;    
}
