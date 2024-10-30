package com.spring.itprofessionals.service;

import com.spring.itprofessionals.dao.ITProfessionalDAO;
import com.spring.itprofessionals.entity.ITProfessional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ITProfessionalServiceImpl implements ITProfessionalService {

    ITProfessionalDAO iTProfessionalDAO;

    @Autowired
    public ITProfessionalServiceImpl(ITProfessionalDAO iTProfessionalDAO) {
        this.iTProfessionalDAO = iTProfessionalDAO;
    }

    @Override
    public List<ITProfessional> findAll() {
        return iTProfessionalDAO.findAll();
    }

    @Override
    public ITProfessional findById(int id) {
        return iTProfessionalDAO.findById(id);
    }

    @Transactional
    @Override
    public ITProfessional save(ITProfessional iTProfessional) {
        return iTProfessionalDAO.save(iTProfessional);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        iTProfessionalDAO.deleteById(id);
    }
}
