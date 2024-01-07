package se.kthraven.messageservice.Persistence.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "message")
public class MessageDB {
    @Id
    private String id;
    private String message;
    @Column(name="send_date")
    private Date sendDate;
    @Column(name = "sender_id")
    private String senderId;
    @Column(name = "receiver_id")
    private String receiverId;

    public MessageDB(){

    }

    public MessageDB(String id, String message, Date sendDate, String senderId, String receiverId) {
        this.id = id;
        this.message = message;
        this.sendDate = sendDate;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
}
