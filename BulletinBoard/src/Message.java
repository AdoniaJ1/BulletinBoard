import java.time.LocalDateTime;
import java.util.Calendar;

enum Group{
    PUBLIC,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE
}

public class Message{

    @Override
    public String toString() {
        return
            " messageID='" + getMessageID() + "'" +
            ", sender='" + getSender() + "'" +
            ", messageDate='" + getMessageDate() + "'" +
            ", subject='" + getSubject() + "'"
            ;
    }

    private int messageID;
    private String sender;
    private LocalDateTime messageDate;
    private String subject;
    private String contents;
    private Group group;

     public Message(int messageID, String sender, LocalDateTime messageDate, String subject, String contents, Group group) {
        this.messageID = messageID;
        this.sender = sender;
        this.messageDate = messageDate;
        this.subject = subject;
        this.contents = contents;
        this.group = group;
    }

    public String toProtocol() {
        return
            getMessageID() +
            ":" + getSender()+
            ":" + getMessageDate()+
            ":" + getSubject() +":" + getGroup()
            + ":" + getContents();
    }

    public Group getGroup() {
        return this.group;
    }

    public int getMessageID() {
        return this.messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public LocalDateTime getMessageDate() {
        return this.messageDate;
    }

    public void setMessageDate(LocalDateTime messageDate) {
        this.messageDate = messageDate;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}