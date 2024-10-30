package com.spring.itprofessionals.dao;

import com.spring.itprofessionals.entity.ITProfessional;

import java.util.List;

public interface ITProfessionalDAO {

    List<ITProfessional> findAll();

    ITProfessional findById(int id);

    ITProfessional save(ITProfessional iTProfessional);

    void deleteById(int id);
}
