package bean.db;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 用户关系的MODEL
 * 用于用户好友关系的实现
 */

@Entity
@Table(name = "TB_USER_FOLLOW")
public class UserFollow {
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

    //发起者
    //多对一你可以关注很多人
    //optional不可选，必须存贮
    @ManyToOne(optional = false)
    @JoinColumn(name = "originId")
    private User origin;

    @Column(nullable = false, updatable = false, insertable = false)
    private String originId;

    @Column(nullable = false, updatable = false, insertable = false)
    private String targetId;

    //发起者关注的人
    @ManyToOne(optional = false)
    @JoinColumn(name = "targetId")
    private User target;

    //对关注人的备注
    @Column
    private String alias;

    //应以创建时间戳，创建时就写入表中
    @CreationTimestamp
    @Column(nullable = false)
    public LocalDateTime createAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOrigin() {
        return origin;
    }

    public void setOrigin(User origin) {
        this.origin = origin;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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
