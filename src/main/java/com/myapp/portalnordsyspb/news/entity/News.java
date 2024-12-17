package com.myapp.portalnordsyspb.news.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition="TEXT")
    private String content;

    @ElementCollection
    @CollectionTable(name="hashtag")
    public Set<String> hashtagList;

    private String photo;

//    @Column(updatable = false)
//    private LocalDate createdAt;
//
//    @PrePersist
//    protected void onCreate() {
//        createdAt = LocalDate.now();
//    }
}
