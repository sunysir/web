package bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_MESSAGE")
public class Message {
    public static final int TYPE_STR = 1;
    public static final int TYPE_PIC = 2;
    public static final int TYPE_FILE = 3;
    public static final int TYPE_AUDIO = 4;

    @Id
    @PrimaryKeyJoinColumn
    //这里不自动生成UUID，避免服务器复杂的映射关系
    //@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(nullable = false, updatable = false)
    private String id;

    //附件允许为null
    @Column
    private String attach;

    //内容不允许为null，类型为text
    @Column(nullable = false, columnDefinition = "TEXT")
    private String  content;

    //注释到另一个表的外键
    @JoinColumn(name = "senderId")
    //发送者不可null
    @ManyToOne(optional = false)
    private User sender;

    @JoinColumn(name = "receiverId")
    //接收者可为null
    @ManyToOne
    private User receiver;

    @Column(nullable = false, updatable = false, insertable = false)
    private String senderId;

    @Column(updatable = false, insertable = false)
    private String receiverId;

    @Column(nullable = false)
    private int type;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;

    @Column(nullable = false, insertable = false, updatable = false)
    private int groupId;

    //应以创建时间戳，创建时就写入表中
    @CreationTimestamp
    @Column(nullable = false)
    public LocalDateTime createAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}


