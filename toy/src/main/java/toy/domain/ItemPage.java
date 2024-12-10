package toy.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ItemPage {
    int curPage=1;
    List<Integer> pages=new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
    List<Item> items;

    public ItemPage(List<Item> items) {
        this.items = items;
    }
}
