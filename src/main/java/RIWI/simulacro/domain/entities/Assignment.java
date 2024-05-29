package RIWI.simulacro.domain.entities;


import java.time.LocalDate;
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

@Entity(name = "assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String assignmentTitle;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String definition;

    @Column(nullable = true)
    private LocalDate duDate;

    @ManyToOne
    @JoinColumn(name="lesson_id", referencedColumnName = "id")
    private Lesson lesson;

    @OneToMany(mappedBy = "assignment" )
    private List<Submission> submisions;
}
