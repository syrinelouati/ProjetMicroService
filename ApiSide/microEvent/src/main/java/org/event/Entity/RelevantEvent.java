package org.event.Entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RelevantEvent {
    private Integer idEvent;
    private String name;

    //private String campPlaceLocation;
    private byte[] image;
}
