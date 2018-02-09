package com.practice1.repository;

import com.practice1.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Alireza on 2/6/2018.
 */
public interface StoreRepository extends JpaRepository<Store,Long> {

}
