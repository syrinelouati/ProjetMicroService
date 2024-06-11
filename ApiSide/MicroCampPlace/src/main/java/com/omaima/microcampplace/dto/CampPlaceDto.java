package com.omaima.microcampplace.dto;
import com.omaima.microcampplace.enumeration.CampPlaceCategory;
import com.omaima.microcampplace.enumeration.State;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CampPlaceDto {
    private Integer idCampPlace;
    private String name;
    private CampPlaceCategory category;
    private int tel;
    private String email;
    private String address;
    private State state;
    private String description;
    private Double longitude ;
    private Double latitude;
}