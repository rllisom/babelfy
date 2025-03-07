package com.babel.babelfy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ArtistDTO {

    private long id;
    private String name;
    private List<Long> id_songDTO;

}
