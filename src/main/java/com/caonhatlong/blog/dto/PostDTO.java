package com.caonhatlong.blog.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {

    private Long id;

    private String content;

    private String title;

    private String username;
}
