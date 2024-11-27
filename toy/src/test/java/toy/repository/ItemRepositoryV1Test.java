package toy.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toy.domain.Item;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryV1Test {
    @Autowired
    ItemRepositoryV1 itemRepository;

    Item testitem;

    @BeforeEach
    void before()  {
        testitem=new Item(1123L,"test1",123, LocalDate.now(),"fantasy,terrific");
    }

    @AfterEach
    void after() {
        itemRepository.delete(1123L);
    }

    @Test
    void save() {
        Long tmp=itemRepository.save(testitem);
        assertThat(tmp).isEqualTo(1123L);
    }

    @Test
    void findById() {
        Long tmp=itemRepository.save(testitem);
        Item item=itemRepository.findById(1123L);
        assertThat(item).isEqualTo(testitem);
    }

    @Test
    void update() {
        Item test2=new Item("test2",142,LocalDate.now(),"sf,blue");
        Item item=itemRepository.update(1123L, test2);
        assertThat(item).isEqualTo(test2);
    }

    @Test
    void delete() {
        itemRepository.delete(1123L);
        assertThatThrownBy(()->itemRepository.findById(1123L)).isInstanceOf(Exception.class);
        itemRepository.save(testitem);
    }

    @Test
    void findAll() {
        List<Item> items=itemRepository.findAll();
        System.out.println("items = " + items);
    }
}