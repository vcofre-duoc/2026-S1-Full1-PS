package cl.duoc.democars.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable =  false, unique = true)
    private String brand;

    @Column(nullable = false)
    private String model;
    
    @Column(nullable = false)
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
