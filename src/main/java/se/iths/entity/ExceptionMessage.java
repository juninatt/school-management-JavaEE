package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.Date;

/**
 * Contains the information sent back when an exception is thrown.
 */
@Entity
public class ExceptionMessage {

    /**
     * The id number of the error message. Value generated automatically with each new instance of the class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long errorNr;

    /**
     * HTTP response status code
     */
    @NotEmpty
    private String statusCode;
    /**
     * Short description of what went wrong
     */
    @NotEmpty
    private String mainMessage;
    /**
     * Optional additional information about the error.
     */
    private String additionalInfo;
    /**
     * When the error occurred.
     */
    private Date occurredAt;

    /**
     * Constructor with 'statusCode', 'mainMessage', and 'additionalInfo' as parameters.
     */
    public ExceptionMessage(String statusCode, String mainMessage, String additionalInfo) {
        this.statusCode = statusCode;
        this.mainMessage = mainMessage;
        this.additionalInfo = additionalInfo;
    }

    /**
     * No-args constructor.
     */
    public ExceptionMessage() {}

    /**
     * Automatically sets 'occurredAt' to current date when new ErrorMessage is created.
     */
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
    public String getAdditionalInfo() {
        return additionalInfo;
    }
    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    public Date getOccurredAt() {
        return occurredAt;
    }
    public void setOccurredAt(Date occurredAt) {
        this.occurredAt = occurredAt;
    }
}
