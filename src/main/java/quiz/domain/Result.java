package quiz.domain;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Result {
    private int id;
    private String userName;
    private String category;
    private Timestamp quiz_start;
    private Timestamp quiz_end;
    private int score;
    private Integer userChoiceId1;
    private Integer userChoiceId2;
    private Integer userChoiceId3;
    private Integer userChoiceId4;
    private Integer userChoiceId5;
}
