package factory;

import bean.db.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.Hib;
import utils.TextUtil;

import java.util.List;
import java.util.UUID;

public class UserFactory {
    public static User findPhone(String phone) {
        return Hib.query(new Hib.Query<User>() {
            @Override
            public User query(Session session) {
                return (User) session
                        .createQuery("from User where phone=:inPhone")
                        .setParameter("inPhone", phone)
                        .uniqueResult();
            }
        });
    }

    public static User findToken(String token) {
        return Hib.query(new Hib.Query<User>() {
            @Override
            public User query(Session session) {
                return (User) session
                        .createQuery("from User where token=:token")
                        .setParameter("token", token)
                        .uniqueResult();
            }
        });
    }

    public static User bindPushId(User user, String pushId) {
        Hib.query(session -> {
            List<User> users = (List<User>) session.createQuery("from User where lower(pushId)=:pushId and id!=:userId")
                    .setParameter("pushId", pushId)
                    .setParameter("userId", user.getId())
                    .list();
            if (users != null) {
                for (User u : users) {
                    u.setPushId(null);
                    session.saveOrUpdate(u);
                }
            }

            user.setPushId(pushId);
            session.saveOrUpdate(user);
            return user;
        });
        if (pushId.equalsIgnoreCase(user.getPushId())){
            return user;
        }else {
            user.setPushId(pushId);
            Hib.query(new Hib.Query<User>() {
                @Override
                public User query(Session session) {
                    session.saveOrUpdate(user);
                    return user;
                }
            });
        }
        return null;
    }

    public static User findName(String name) {
        return Hib.query(new Hib.Query<User>() {
            @Override
            public User query(Session session) {
                return (User) session
                        .createQuery("from User where name=:iName")
                        .setParameter("iName", name)
                        .uniqueResult();
            }
        });
    }

    public static User register(String account, String password, String name) {
        User user = createUser(account, password, name);
        if (user != null) {
            user = login(user);
        }
        return user;
    }

    public static User login(String account, String password) {
        User user = Hib.query(new Hib.Query<User>() {
            @Override
            public User query(Session session) {
                String passwordMd5 = TextUtil.getMD5(password);
                return (User) session
                        .createQuery("from User where phone=:phone and password=:password")
                        .setParameter("phone", account.trim())
                        .setParameter("password", TextUtil.encodeBase64(passwordMd5))
                        .uniqueResult();
            }
        });
        if (user != null) {
            user = login(user);
        }
        return user;
    }

    public static User login(User user) {
        String newToken = UUID.randomUUID().toString();
        newToken = TextUtil.encodeBase64(newToken);
        user.setToken(newToken);
        return Hib.query(new Hib.Query<User>() {
            @Override
            public User query(Session session) {
                session.saveOrUpdate(user);
                return user;
            }
        });
    }

    public static User updateInfo(User user, String name, String portrait, String desc, int sex){
        user.setName(name);
        user.setPortrait(portrait);
        user.setDescriptioin(desc);
        user.setSex(sex);
        return Hib.query(session -> {
            session.saveOrUpdate(user);
            return user;
        });
    }

    public static User createUser(String account, String password, String name) {
        account = account.trim();
        password = getEncodePassword(password);
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setPhone(account);
        Session session = Hib.session();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return null;
    }

    public static String getEncodePassword(String password) {
        String passwordMd5 = TextUtil.getMD5(password);
        return TextUtil.encodeBase64(passwordMd5);
    }
}
