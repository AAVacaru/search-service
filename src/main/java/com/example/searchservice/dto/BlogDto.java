package com.example.searchservice.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogDto {
    private Long blogId;
    private String title;
    private String content;
    private String category;
    private LocalDateTime date;
    private String authorName;
    private Set<PictureDto> pictures;
}
