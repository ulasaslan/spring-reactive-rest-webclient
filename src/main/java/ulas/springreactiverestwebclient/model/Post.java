package ulas.springreactiverestwebclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private String userId;
    private String id;
    private String title;
    private String body;
}
