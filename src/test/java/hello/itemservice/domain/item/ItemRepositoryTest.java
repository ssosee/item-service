package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Nested
    class itemTest {

        @Test
        @DisplayName("아이템 저장")
        void save() {
            //given
            Item item = new Item("itemA", 10000, 10);
            //when
            Item saveItem = itemRepository.save(item);
            //then
            Item findItem = itemRepository.findById(item.getId());
            Assertions.assertThat(findItem).isEqualTo(saveItem);
        }

        @Test
        @DisplayName("아이템 전체 조회")
        void findAll() {
            //given
            Item item1 = new Item("item1", 10000, 10);
            Item item2 = new Item("item2", 20000, 20);
            itemRepository.save(item1);
            itemRepository.save(item2);
            //when
            List<Item> result = itemRepository.findAll();
            //then
            Assertions.assertThat(result.size()).isEqualTo(2);
            Assertions.assertThat(result).contains(item1, item2);

        }

        @Test
        @DisplayName("아이템 업데이료")
        void updateItem() {
            //given
            Item item = new Item("item1", 10000, 10);

            Item savedItem = itemRepository.save(item);
            Long itemId = savedItem.getId();

            //when
            Item updateParam = new Item("item1", 30000, 30);
            itemRepository.update(itemId, updateParam);
            //then
            Item findItem = itemRepository.findById(itemId);
            Assertions.assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
            Assertions.assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
            Assertions.assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
        }
    }
}