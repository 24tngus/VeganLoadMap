package com.mission.mymission.repository;


import com.mission.mymission.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByshopseq(int shopseq);
}