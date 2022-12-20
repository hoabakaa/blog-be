package com.caonhatlong.blog.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String title;

    @Lob
    @NotEmpty
    @Column
    private String content;

    @Column
    private Instant createAt;

    @Column
    private Instant updateAt;

    @NotBlank
    @Column
    private String userName;
}
