package ulas.springreactiverestwebclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Photos {
    private String albumId;
    private String id;
    private String title;
    private String url;
    private String thumbnailUrl;
}
