package ru.practicum.shareit.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByUserIdOrderById(long userId);

    @Query(value = "SELECT i FROM Item i WHERE lower(i.name) like %?1% OR lower(i.description) like %?1% " +
            "AND i.available=TRUE")
    List<Item> findByNameOrDescriptionLike(String text);

    void deleteById(long itemId);
}