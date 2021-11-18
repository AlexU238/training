package com.u238.training.dao;

import com.u238.training.entity.User;
import com.u238.training.entity.UserProfile;
import com.u238.training.utils.SortUtilsUserProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserProfileDAOImpl implements UserProfileDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public UserProfileDAOImpl(@Qualifier("userSessionFactory") SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<UserProfile> getProfiles() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<UserProfile> query = currentSession.createQuery("from UserProfile order by lastName", UserProfile.class);

        return query.getResultList();
    }


    @Override
    public UserProfile getUserProfile(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        return currentSession.get(UserProfile.class, id);
    }

    @Override
    public void deleteUser(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.delete(getUserProfile(id));
    }

    @Override
    public List searchUserProfile(String theSearchName) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery;

        // only search by name if theSearchName is not empty
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery = currentSession.createQuery("from UserProfile where lower(firstName) like :theName or lower(lastName) like :theName",UserProfile.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        } else {
            // theSearchName is empty ... so just get all customers
            theQuery = currentSession.createQuery("from UserProfile ", UserProfile.class);
        }

        return theQuery.getResultList();
    }

    @Override
    public List<UserProfile> getUserProfiles(int theSortField) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // determine sort field
        String theFieldName;

        switch (theSortField) {
            case SortUtilsUserProfile.FIRST_NAME:
                theFieldName = "firstName";
                break;
            case SortUtilsUserProfile.LAST_NAME:
                theFieldName = "lastName";
                break;
            case SortUtilsUserProfile.EMAIL:
                theFieldName = "email";
                break;
            default:
                // if nothing matches the default to sort by lastName
                theFieldName = "lastName";
        }
        // create a query
        String queryString = "from UserProfile order by " + theFieldName;
        Query<UserProfile> theQuery = currentSession.createQuery(queryString, UserProfile.class);

        // execute query and return result list
        return theQuery.getResultList();
    }

    @Override
    public void saveUserProfile(UserProfile userProfile) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(userProfile);
    }
}
