package bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PUSH_HISTORY")
public class PushHistory {
    @Id
    //主键
    @PrimaryKeyJoinColumn
    //id类型为UUID
    @GeneratedValue(generator = "uuid")
    //映射为hibernate的uuid2，带横杠（优化）
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    //是一个列
    @Column(updatable = false, nullable = false)
    private String id;

    //存储的都是jason字符串
    //大字段映射支持
    @Lob
    @Column(nullable = false, columnDefinition = "BLOB")
    private String entity;

    @Column(nullable = false)
    private int entityType;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "receiverId")
    private User receiver;
    @Column(nullable = false, insertable = false, updatable = false)
    private String receiverId;

    //接收者的当前设备推送ID
    @Column
    private String receiverPushId;

    //发送者Id 可为空，因为可能是系统消息
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "senderId")
    private User sender;
    @Column(insertable = false, updatable = false)
    private String senderId;

    //消息送达的时间
    @CreationTimestamp
    @Column
    public LocalDateTime arrivalAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();
}
