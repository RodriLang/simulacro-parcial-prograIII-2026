package com.utn.simulacro.dtos.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GameResponseDto {

    private Long id;

    private String name;

    private String category;

    private Integer minAge;

    private Integer availableStock;

}
