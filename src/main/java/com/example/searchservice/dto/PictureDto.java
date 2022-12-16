package com.example.searchservice.dto;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PictureDto {

    private Long pictureId;
    private String name;
    private String type;

    private byte[] picByte;

    public PictureDto(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }
}
