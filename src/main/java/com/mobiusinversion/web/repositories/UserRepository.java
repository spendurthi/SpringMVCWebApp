package com.mobiusinversion.web.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mobiusinversion.web.entities.User;

@Repository
@Transactional
public class UserRepository {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<User> getAllUsers() {
        return hibernateTemplate.loadAll(User.class);
    }

    public Integer createUser(User user) {
        return hibernateTemplate.merge(user).getId();
    }

}