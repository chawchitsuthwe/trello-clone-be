package com.seclub.trelloclone.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.seclub.trelloclone.models.Account;
import com.seclub.trelloclone.models.Label;
import com.seclub.trelloclone.repositories.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.seclub.trelloclone.models.Card;

import javax.transaction.Transactional;

@RestController
@CrossOrigin
@RequestMapping("card")
public class CardController {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    ListRepository listRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    LabelRepository labelRepository;
    @Autowired
    ChecklistRepository checklistRepository;

    @GetMapping
    @ResponseBody
    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    @GetMapping("{id}")
    public Card getById(@PathVariable Long id) {
        return cardRepository.getOne(id);
    }

    @PostMapping()
    public Card save(@RequestBody Card card){
        return cardRepository.saveAndFlush(card);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Card update(@PathVariable Long id, @RequestBody Card card) {
        Card oldCard = cardRepository.getOne(id);
        BeanUtils.copyProperties(card, oldCard, "id");
        return cardRepository.saveAndFlush(oldCard);
    }

    @RequestMapping(value = "{id}/{username}", method = RequestMethod.POST)
    public Card addAccountToCard(@PathVariable Long id, @PathVariable String username) {
        Card card = cardRepository.getOne(id);
        Account account = accountRepository.getOne(username);

        java.util.List<Account> accounts = new ArrayList<Account>();
        accounts.add(account);
        card.setAccounts(accounts);
        return cardRepository.save(card);

    }

    @RequestMapping(value = "{card_id}/label/{label_id}", method = RequestMethod.POST)
    public Card addLabelToCard(@PathVariable Long card_id, @PathVariable Long label_id) {
        Card card = cardRepository.getOne(card_id);
        Label label = labelRepository.getOne(label_id);

        java.util.List<Label> labels = new ArrayList<Label>();
        labels.add(label);
        card.setLabels(labels);
        return cardRepository.save(card);

    }

    @RequestMapping(value = "{card_id}/list/{list_id}", method = RequestMethod.PUT)
    public Card changeList(@PathVariable Long card_id, @PathVariable Long list_id) {
        Card card = cardRepository.getOne(card_id);
        com.seclub.trelloclone.models.List newList = listRepository.getOne(list_id);

        card.setList(newList);
        return cardRepository.save(card);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        cardRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}/{username}", method = RequestMethod.DELETE)
    public void deleteAccountFromCard(@PathVariable Long id, @PathVariable String username) {
        Card card = cardRepository.getOne(id);
        java.util.List<Account> accounts = card.getAccounts();

        Iterator<Account> it = accounts.iterator();
        while (it.hasNext()) {
            Account account = it.next();
            if (account.getUsername().equals(username)) {
                it.remove();
            }
        }

        card.setAccounts(accounts);
        cardRepository.save(card);

    }

    @RequestMapping(value = "{card_id}/label/{label_id}", method = RequestMethod.DELETE)
    public void deleteLabelFromCard(@PathVariable Long card_id, @PathVariable Long label_id) {
        Card card = cardRepository.getOne(card_id);
        java.util.List<Label> labels = card.getLabels();

        Iterator<Label> it = labels.iterator();
        while (it.hasNext()) {
            Label label = it.next();
            if (label.getId().equals(label_id)) {
                it.remove();
            }
        }

        card.setLabels(labels);
        cardRepository.save(card);

    }

    @Transactional
    @RequestMapping(value = "{card_id}/checklist", method = RequestMethod.DELETE)
    public Long deleteChecklistByCardId(@PathVariable Long card_id) {
        return checklistRepository.deleteByCardId(card_id);
    }

    @RequestMapping(value = "{id}/{status}", method = RequestMethod.PUT)
    public void archiveOrDelete(@PathVariable Long id, @PathVariable Integer status) {
        Card card = cardRepository.getOne(id);

        if(status == 1 || status == 2){
            card.setStatus(status);
            cardRepository.save(card);
        }
        else if(status == 3){
            cardRepository.deleteById(id);
        }
    }


}