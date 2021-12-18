package dto;

public class UpdateDTO {
    Integer resolverId;
    Integer statusId;

    public UpdateDTO() {
    }

    public UpdateDTO(Integer resolverId, Integer statusId) {
        this.resolverId = resolverId;
        this.statusId = statusId;
    }

    public Integer getResolverId() {
        return resolverId;
    }

    public void setResolverId(Integer resolverId) {
        this.resolverId = resolverId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "UpdateDTO{" +
                "resolverId=" + resolverId +
                ", statusId=" + statusId +
                '}';
    }
}
