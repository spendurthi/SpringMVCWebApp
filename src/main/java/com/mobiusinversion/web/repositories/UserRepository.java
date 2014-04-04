package com.mobiusinversion.web.repositories;

import java.util.List;

import com.mobiusinversion.web.hibernate.SessionService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mobiusinversion.web.entities.User;

@Transactional
@Repository
public class UserRepository {

    private static SessionFactory sessionFactory = SessionService.getSessionFactory();

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }

    @SuppressWarnings("unchecked")
    public Integer createUser(User user) {
        User mergedUser = (User) sessionFactory.getCurrentSession().merge(user);
        return mergedUser.getId();
    }

}


