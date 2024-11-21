package toy.repository;

import toy.domain.Item;

import java.util.List;

public interface ItemRepository {
    public Long save(Item item);
    public Item findById(Long id);
    public Item update(Long id, Item updateParam);
    public void delete(Long id);
    public List<Item> findAll();
}
