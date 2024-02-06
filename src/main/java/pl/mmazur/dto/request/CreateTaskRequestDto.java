package pl.mmazur.dto.request;

import lombok.Data;

@Data
public class CreateTaskRequestDto {
    private String name;
    private String description;
    private String status;
    private String assignees;
    private String timeEstimate;
    private String priority;
    private String parent;
    private boolean archived;

}
