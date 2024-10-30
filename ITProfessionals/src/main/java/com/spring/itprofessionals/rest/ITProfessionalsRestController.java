package com.spring.itprofessionals.rest;

import com.spring.itprofessionals.entity.ITProfessional;
import com.spring.itprofessionals.service.ITProfessionalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/it")
public class ITProfessionalsRestController {

    ITProfessionalService itProfessionalService;

    public ITProfessionalsRestController(ITProfessionalService itProfessionalService) {
        this.itProfessionalService = itProfessionalService;
    }

    @GetMapping("/itprofessionals")
       public List<ITProfessional> findAll() {
        return itProfessionalService.findAll();
    }

    @GetMapping("/itprofessionals/{itprofessionalId}")
    public ITProfessional findById(@PathVariable int itprofessionalId) {
        ITProfessional iTProfessional = itProfessionalService.findById(itprofessionalId);
        if (iTProfessional == null) {
            throw new RuntimeException("ITProfessional with id " + itprofessionalId + " not found.");
        }
        return iTProfessional;
    }

    @PostMapping("/itprofessionals")
    public ITProfessional create(@RequestBody ITProfessional iTProfessional) {
        iTProfessional.setId(0);
        return itProfessionalService.save(iTProfessional);
    }
    @PutMapping("/itprofessionals")
    public ITProfessional update(@RequestBody ITProfessional itProfessional) {
        ITProfessional iTProfessional = itProfessionalService.save(itProfessional);
        return iTProfessional;
    }
    @DeleteMapping("itprofessionals/{itprofessionalId}")
    public String delete(@PathVariable int itprofessionalId) {
        ITProfessional tempITProfessional = itProfessionalService.findById(itprofessionalId);
        if (tempITProfessional == null) {
            throw new RuntimeException("ITProfessional with id " + itprofessionalId + " not found.");
        }
        itProfessionalService.deleteById(itprofessionalId);
        return "Deleted itprofessional with id " + itprofessionalId;

    }
}









