package quiz.domain;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    private int id;
    private String username;
    private Timestamp sent_time;
    private String content;
}
