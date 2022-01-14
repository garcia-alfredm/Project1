package models;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class Reimbursement {
    Integer id;
    BigDecimal amount;
    String submitted;
    String resolved;
    String description;
    //receipt is a bytea
    byte[] receiptImg;
    /* Foreign Keys */
    /* when using inner join to get queries*/
    String authorUserName;
    String authorFirstName;
    String authorLastName;
    String authorEmail;
    String status;
    String type;
    /* when using create */
    Integer author;
    Integer resolver;
    Integer statusId;
    Integer typeId;

    public Reimbursement() {
    }

    public Reimbursement(Integer id, BigDecimal amount, String submitted, String resolved,
                         String description, byte[] receiptImg, Integer author,
                         Integer resolver, Integer statusId, Integer typeId) {
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receiptImg = receiptImg;
        this.author = author;
        this.resolver = resolver;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public Reimbursement(Integer id, BigDecimal amount, String submitted, String resolved,
                         String description, byte[] receiptImg, String authorUserName,
                         String authorFirstName, String authorLastName, String authorEmail,
                         String status, String type, Integer resolver) {
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receiptImg = receiptImg;
        this.authorUserName = authorUserName;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.authorEmail = authorEmail;
        this.status = status;
        this.type = type;
        this.resolver = resolver;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public String getResolved() {
        return resolved;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getReceiptImg() {
        return receiptImg;
    }

    public void setReceiptImg(byte[] receiptImg) {
        this.receiptImg = receiptImg;
    }

    public String getAuthorUserName() {
        return authorUserName;
    }

    public void setAuthorUserName(String authorUserName) {
        this.authorUserName = authorUserName;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Integer getResolver() {
        return resolver;
    }

    public void setResolver(Integer resolver) {
        this.resolver = resolver;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", amount=" + amount +
                ", submitted='" + submitted + '\'' +
                ", resolved='" + resolved + '\'' +
                ", description='" + description + '\'' +
                ", receiptImg=" + Arrays.toString(receiptImg) +
                ", authorUserName='" + authorUserName + '\'' +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                ", authorEmail='" + authorEmail + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", resolver=" + resolver +
                '}';
    }
}
