package RIWI.simulacro.domain.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name="sender_id", referencedColumnName = "id")
    private User sender;

    @ManyToOne
    @JoinColumn(name="reciver_id", referencedColumnName = "id")
    private User reciver;

    @ManyToOne
    @JoinColumn(name="course_id", referencedColumnName = "id")
    private Course course; 
    
}
