package com.quickboard.resourcepost.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PostSearchCondition{
    private String title;
    private String content;
    private Long profileId;
}
