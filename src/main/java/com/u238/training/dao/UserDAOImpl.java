package com.u238.training.dao;

import com.u238.training.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(@Qualifier("userSessionFactory") SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getUsers() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<User> query = currentSession.createQuery("from User order by id", User.class);

        return query.getResultList();
    }

    @Override
    public void saveUser(User user) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(user);
    }

    @Override
    public User getUser(String username) {
        Session currentSession = sessionFactory.getCurrentSession();

        return currentSession.get(User.class, username);
    }

    @Override
    public void deleteUser(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
		Query query=currentSession.createQuery("delete from User where username=:userUsername");
		query.setParameter("userUsername", username);
		query.executeUpdate();
    }

    @Override
    public List searchUser(String theSearchName) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery;

        // only search by name if theSearchName is not empty
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery = currentSession.createQuery("from User where lower(username) like :theName",User.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        } else {
            // theSearchName is empty ... so just get all customers
            theQuery = currentSession.createQuery("from User ", User.class);
        }

        return theQuery.getResultList();
    }


}
