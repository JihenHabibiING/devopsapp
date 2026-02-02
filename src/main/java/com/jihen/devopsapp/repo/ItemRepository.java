package com.jihen.devopsapp.repo;

import com.jihen.devopsapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {}
