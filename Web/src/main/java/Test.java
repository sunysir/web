import bean.db.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDateTime;

public class Test {

    public static void main(String[] args) {
        try {
            User user = new User();
            user.setName("sunyang1");
            user.setPassword("1234561");
            user.setPushId("1232");
            user.setDescriptioin("abcf");
            user.setToken("dasdce1");
            user.setPortrait("ab3");
            user.setPhone("15765541191");
            System.out.println("" + LocalDateTime.now());
            Configuration config = new Configuration();
            SessionFactory factory = config.configure().buildSessionFactory();
            Session session = factory.openSession();
            Transaction tran = session.beginTransaction();//开始事物
            session.save(user);//执行
            tran.commit();//提交
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
