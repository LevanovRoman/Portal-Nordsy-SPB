package com.myapp.portalnordsyspb.news.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "news_category",
//            joinColumns = @JoinColumn(name = "news_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id"))
//    @JsonManagedReference
//    private List<Category> categoryList = new ArrayList<>();
}
