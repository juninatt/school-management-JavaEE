package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.Date;

@Entity
public class ExceptionMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long errorNr;

    @NotEmpty
    private String statusCode;

    @NotEmpty
    private String mainMessage;

    private String additionalInfo;

    private Date occurredAt;

    public ExceptionMessage(String statusCode, String mainMessage, String additionalInfo) {
        this.statusCode = statusCode;
        this.mainMessage = mainMessage;
        this.additionalInfo = additionalInfo;
    }

    public ExceptionMessage() {}

    @PrePersist
    public void getCurrentDate() {
        setOccurredAt(Date.from(Instant.now()));
    }

    public long getErrorNr() {
        return errorNr;
    }

    public void setErrorNr(long id) {
        this.errorNr = id;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMainMessage() {
        return mainMessage;
    }

    public void setMainMessage(String mainMessage) {
        this.mainMessage = mainMessage;
    }

    public Date getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(Date occurredAt) {
        this.occurredAt = occurredAt;
    }
}
