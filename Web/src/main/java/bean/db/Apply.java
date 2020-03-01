package bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_APPLY")
public class Apply {
    private static final int TYPE_ADD_USER = 1;//好友
    private static final int TYPE_ADD_GROUP = 2;//群

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

    @JoinColumn(name = "applicantId")
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User applicant;
    //可为空位系统人员
    @Column(insertable = false, updatable = false)
    private String applicantId;

    //附件，可以附带图片地址，或者其他
    @Column(columnDefinition = "TEXT")
    private String attach;

    //对我们申请的信息做描述
    //eg:我想加你为好友
    @Column(nullable = false)
    private String decription;

    //不进行强关联，不建立主外键关联
    //type->TYPE_ADD_USER:User.id
    //type->TYPE_ADD_GROUP:Group.id
    @Column(nullable = false, insertable = false, updatable = false)
    private String targetId;

    @Column(nullable = false)
    private int type;

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

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
