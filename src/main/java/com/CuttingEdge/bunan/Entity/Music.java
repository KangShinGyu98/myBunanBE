package com.CuttingEdge.bunan.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String singer;
    private String writer;
    private Date released;
    private Date posted;
    private Date modified;
    private Date deleted;
    private String videoId;
    private Long likes;
    private Long views;
    private String country;
    private String genre;


    // 생성자, getter 및 setter
}