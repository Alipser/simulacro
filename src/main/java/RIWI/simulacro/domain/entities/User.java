package RIWI.simulacro.domain.entities;

import java.util.List;

import RIWI.simulacro.utils.enums.RoleUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity (name ="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 35, nullable = false)
    private String userName;
    @Column(length = 64, nullable = false)
    private String password;
    @Column(length = 100, nullable = false)
    private String email;
    @Column(length = 70, nullable = false)
    private String fullName;
    
    private RoleUser rol;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude                                     
    @OneToMany(mappedBy = "id")
    private List<Course> insTructingCourses;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user")
    private List<Enrollment> enrollments;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "sender")
    private List<Message> sended; // Dividido en dos listas separadas

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "reciver")
    private List<Message> recived; // Dividido en dos listas separadas


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user")
    private List<Submission> submisions;
    
}
