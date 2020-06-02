package com.seclub.trelloclone.controllers;

import com.seclub.trelloclone.models.Card;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.seclub.trelloclone.models.List;
import com.seclub.trelloclone.repositories.ListRepository;

@RestController
@CrossOrigin
@RequestMapping("list")
public class ListController {

    @Autowired
    ListRepository listRepository;

    @GetMapping
    public java.util.List<List> getAll() {
        return listRepository.findAll();
    }

    @GetMapping("position/{position}")
    public java.util.List<List> getByPositionGTE(@PathVariable Integer position) {
        return listRepository.findByPositionGreaterThanEqual(position);
    }

    @GetMapping("title/{searchTerm}")
    public java.util.List<List> getByTitleContaining(@PathVariable String searchTerm) {
        return listRepository.findByTitleContaining(searchTerm);
    }

    @PostMapping()
    public List save(@RequestBody List list){
        return listRepository.saveAndFlush(list);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public List update(@PathVariable Long id, @RequestBody List list) {
        List oldList = listRepository.getOne(id);
        BeanUtils.copyProperties(list, oldList, "id");
        return listRepository.saveAndFlush(oldList);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        listRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}/{status}", method = RequestMethod.PUT)
    public void archiveOrDelete(@PathVariable Long id, @PathVariable Integer status) {
        List list = listRepository.getOne(id);

        if(status == 1 || status == 2){
            list.setStatus(status);
            listRepository.save(list);
        }
        else if(status == 3){
            listRepository.deleteById(id);
        }
    }
}