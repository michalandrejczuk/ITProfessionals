package com.spring.itprofessionals.dao;

import com.spring.itprofessionals.entity.ITProfessional;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ITProfessionalDAOImpl implements ITProfessionalDAO {

    EntityManager entityManager;

    @Autowired
    public ITProfessionalDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ITProfessional> findAll() {
        return entityManager.createQuery("from ITProfessional", ITProfessional.class).getResultList();
    }

    @Override
    public ITProfessional findById(int id) {
        ITProfessional iTProfessional = entityManager.find(ITProfessional.class, id);
        return iTProfessional;
    }

    @Override
    public ITProfessional save(ITProfessional iTProfessional) {
        if (iTProfessional == null) {
            throw new IllegalArgumentException("The given ITProfessional is null");
        }
        iTProfessional  = entityManager.merge(iTProfessional);
        return iTProfessional;
    }

    @Override
    public void deleteById(int id) {
        ITProfessional itProfessional = entityManager.find(ITProfessional.class, id);
        if (itProfessional != null) {
            entityManager.remove(itProfessional);
        }
    }
}
