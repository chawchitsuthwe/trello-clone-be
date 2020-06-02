package com.seclub.trelloclone.controllers;

import com.seclub.trelloclone.models.Label;
import com.seclub.trelloclone.repositories.LabelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("label")
public class LabelController {

    @Autowired
    LabelRepository labelRepository;

    @GetMapping
    public List<Label> getAll() {
        return labelRepository.findAll();
    }

    @GetMapping("{id}")
    public Label getById(@PathVariable Long id) {
        return labelRepository.getOne(id);
    }

    @PostMapping()
    public Label save(@RequestBody Label label) {
        return labelRepository.saveAndFlush(label);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Label update(@PathVariable Long id, @RequestBody Label label) {
        Label oldLabel = labelRepository.getOne(id);
        BeanUtils.copyProperties(label, oldLabel, "id");
        return labelRepository.saveAndFlush(oldLabel);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        labelRepository.deleteById(id);
    }


}
