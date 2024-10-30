package com.spring.itprofessionals.service;

import com.spring.itprofessionals.entity.ITProfessional;

import java.util.List;

public interface ITProfessionalService {

    List<ITProfessional> findAll();

    ITProfessional findById(int id);

    ITProfessional save(ITProfessional iTProfessional);

    void deleteById(int id);
}
