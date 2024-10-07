package com.myapp.portalnordsyspb.news.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "category_news_table",
    joinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "news_id", referencedColumnName = "id")})
    @JsonManagedReference
    private List<News> newsList;

}
