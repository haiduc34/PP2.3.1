package hiber.dao;

import org.springframework.stereotype.Component;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import hiber.model.User;


import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);

        if(user !=null){
            session.delete(user);
        }
    }

    @Override
    public User getUserById(int id) {
        //Session session =this.sessionFactory.getCurrentSession();
        Session session = sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
        System.out.println(user);

        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();

        return userList;
    }
}
