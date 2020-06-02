package com.seclub.trelloclone.controllers;

import com.seclub.trelloclone.models.Checklist;
import com.seclub.trelloclone.repositories.ChecklistRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("checklist")
public class ChecklistController {

    @Autowired
    ChecklistRepository checklistRepository;

    @GetMapping
    @ResponseBody
    public List<Checklist> getAll() {
        return checklistRepository.findAll();
    }

    @GetMapping("{id}")
    public Checklist getById(@PathVariable Long id) {
        return checklistRepository.getOne(id);
    }

    @PostMapping()
    public Checklist save(@RequestBody Checklist checklist){
        return checklistRepository.saveAndFlush(checklist);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Checklist update(@PathVariable Long id, @RequestBody Checklist checklist) {
        Checklist oldChecklist = checklistRepository.getOne(id);
        BeanUtils.copyProperties(checklist, oldChecklist, "id");
        return checklistRepository.saveAndFlush(oldChecklist);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        checklistRepository.deleteById(id);
    }
}
