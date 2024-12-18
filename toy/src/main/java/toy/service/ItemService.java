package toy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.domain.Item;
import toy.domain.ItemRegisterDTO;
import toy.domain.ItemViewDTO;
import toy.repository.ItemRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//domain은 web을 참고해선 안된다

@Service
@RequiredArgsConstructor
public class ItemService {
    final ItemRepository itemRepository;
    Long sequence_id=0L;

    public void save(ItemRegisterDTO itemRegisterDTO, String uuid) {
        Item item=new Item(sequence_id++,uuid, itemRegisterDTO.getPoint(), LocalDate.now(), itemRegisterDTO.getTags_str());
        itemRepository.save(item);
    }

    public List<Item> item_all() {
        List<Item> all = itemRepository.findAll();
        return all;
    }

    public ItemViewDTO itemView(Long itemId) {
        Item byId = itemRepository.findById(itemId);
        ItemViewDTO dto=new ItemViewDTO(byId.getItem_id(),byId.getPoint(),byId.getTags_str(), byId.getFileName());
        return dto;
    }

    public List<ItemViewDTO> itemPage(int curPage) {
        List<Item> list=itemRepository.findPage(curPage);
        List<ItemViewDTO> retList=new ArrayList<ItemViewDTO>();
        for(Item item : list) {
            ItemViewDTO dto=new ItemViewDTO(item.getItem_id(), item.getPoint(),item.getTags_str(),item.getFileName());
            retList.add(dto);
        }
        return retList;
    }
}
