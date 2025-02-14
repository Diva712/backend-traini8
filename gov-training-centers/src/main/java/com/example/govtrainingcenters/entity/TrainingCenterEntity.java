package com.example.govtrainingcenters.entity;



import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "training_centers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingCenterEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String centerName;
    private String centerCode;

    @Embedded
    private AddressEntity address;

    private Integer studentCapacity;

    @ElementCollection
    @CollectionTable(
        name = "courses_offered", 
        joinColumns = @JoinColumn(
            name = "training_center_id",
            foreignKey = @ForeignKey(foreignKeyDefinition = "FOREIGN KEY (training_center_id) REFERENCES training_centers(id) ON DELETE CASCADE")
        )
    )
    @Column(name = "course_name")
    private List<String> coursesOffered;

    private String contactEmail;
    private String contactPhone;

    private Long createdOn;
}
