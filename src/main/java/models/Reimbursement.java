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
    Integer author;
//    String authorUserName;
//    String authorFirstName;
//    String authorLastName;
//    String authorEmail;
    Integer resolver;
    //String resolverFirstName;
    //String resolverLastName;
    //String resolverEmail
    Integer status;
    //String status;
    Integer typeId;
    //String type;

    public Reimbursement() {
    }

    public Reimbursement(Integer id, BigDecimal amount, String submitted, String resolved,
                         String description, byte[] receiptImg, Integer author,
                         Integer resolver, Integer status, Integer typeId) {
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receiptImg = receiptImg;
        this.author = author;
        this.resolver = resolver;
        this.status = status;
        this.typeId = typeId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
                ", author=" + author +
                ", resolver=" + resolver +
                ", status=" + status +
                ", typeId=" + typeId +
                '}';
    }
}
