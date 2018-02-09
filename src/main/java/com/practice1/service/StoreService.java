package com.practice1.service;

import com.practice1.model.Store;
import com.practice1.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

/**
 * Created by Alireza on 2/6/2018.
 */

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    public ResponseEntity<?> storeOrder(@RequestBody @Validated Store store){
            Store store1 = new Store();
            store1.setName(store.getName());
            storeRepository.save(store1);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateStore(@RequestBody @Validated Store store){
        Store store1 = storeRepository.findOne(store.getId());
        store1.setName(store.getName());
        storeRepository.save(store1);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> deleteStore(Long id){
        storeRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public Store getOneStore(Long id){
        return storeRepository.findOne(id) ;
    }

    public Collection<Store> getAllStores(){
        return storeRepository.findAll();
    }

}
