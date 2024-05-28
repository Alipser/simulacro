package RIWI.simulacro.domain.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "messages")
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String messageContent;

    @Column
    private LocalDate sendDate;

    @OneToMany
    @JoinColumn(name="sender_id", referencedColumnName = "id")
    private User sender;

    @OneToMany
    @JoinColumn(name="reciver_id", referencedColumnName = "id")
    private User reciver;

    @OneToMany
    @JoinColumn(name="course_id", referencedColumnName = "id")
    private Course course; 
    
}
