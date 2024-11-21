package toy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.domain.Item;
import toy.domain.ItemDTO;
import toy.repository.ItemRepository;

import java.time.LocalDate;
import java.util.List;

//domain은 web을 참고해선 안된다

@Service
@RequiredArgsConstructor
public class ItemService {
    final ItemRepository itemRepository;
    Long sequence_id=0L;

    public void save(ItemDTO itemDTO, String uuid) {
        Item item=new Item(sequence_id++,uuid, itemDTO.getPoint(), LocalDate.now(),itemDTO.getTags_str());
        itemRepository.save(item);
    }

    public List<Item> item_all() {
        List<Item> all = itemRepository.findAll();
        return all;
    }

    public Item item_view(Long id) {
        return itemRepository.findById(id);
    }
}
