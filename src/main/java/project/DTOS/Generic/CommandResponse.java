package project.DTOS.Generic;

public class CommandResponse {
    private boolean success;
    private Long id;

    public CommandResponse(boolean success, Long id) {
        this.success = success;
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public Long getId() {
        return id;
    }
}
