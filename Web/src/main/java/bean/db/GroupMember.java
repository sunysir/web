package bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_MEMBER")
public class GroupMember {
    public static final int PERMISSION_TYPE_NONE = 0;//普通成员
    public static final int PERMISSION_TYPE_ADMIN = 1;//群管理员
    public static final int PERMISSION_TYPE_ADMIN_SU = 100;//创建者

    public static final int NOTIFY_LEVLE_INVALID = -1;//不接受消息
    public static final int NOTIFY_LEVLE_NONE = 0; //默认接收消息
    public static final int NOTIFY_LEVLE_CLOSE = 1;//接收消息不提示

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

    @Column
    private String alias;

    @Column(nullable = false)
    private int notifyLevel = NOTIFY_LEVLE_NONE;

    @Column(nullable = false)
    private int permissionType = PERMISSION_TYPE_NONE;

    //应以创建时间戳，创建时就写入表中
    @CreationTimestamp
    @Column(nullable = false)
    public LocalDateTime createAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    @JoinColumn(name = "userId")
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

    @Column(nullable = false, insertable = false, updatable = false)
    private String userId;

    @JoinColumn(name = "groupId")
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Group group;
    @Column(nullable = false, insertable = false, updatable = false)
    private String groupId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getNotifyLevel() {
        return notifyLevel;
    }

    public void setNotifyLevel(int notifyLevel) {
        this.notifyLevel = notifyLevel;
    }

    public int getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(int permissionType) {
        this.permissionType = permissionType;
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
