package project.DTOS.Generic;

public class CommandResponse {
    private final boolean success;
    private final Long id;

    public CommandResponse(boolean success, Long id) {
        this.success = success;
        this.id = id;
    }

    public final boolean isSuccess() {
        return success;
    }

    public final Long getId() {
        return id;
    }
}
