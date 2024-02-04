package com.example.libraryback.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String header;

    @Column(nullable = false)
    private String subtitle;

    @Column(nullable = false, columnDefinition = "text")
    private String description;

    @ManyToOne
    private FileImg backgroundImage;
}
