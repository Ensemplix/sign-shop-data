package ru.ensemplix.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Предоставляет информацию, о всех возможных предметов в торговле.
 */
public class ShopItemRegistry {

    // Список товаров, где в качестве ключа выступает название товара.
    private final Map<String, ShopItem> itemsByName = new HashMap<>();
    // Список товаров, где в качестве ключа выступает идентификатор.
    private final Map<String, List<ShopItem>> itemsById = new HashMap<>();

    /**
     * Добавляет переданный товар в перечень.
     *
     * @param item Товар, который добавляем в перечень.
     */
    public void addItem(ShopItem item) {
        String id = item.getItemStack().getId();
        String name = item.getName();

        if(itemsByName.containsKey(name)) {
            throw new IllegalArgumentException("Item with name " + name + " already registered");
        }

        List<ShopItem> items = itemsById.get(id);

        if(items == null) {
            items = new ArrayList<>();
            itemsById.put(id, items);
        }

        itemsByName.put(name, item);
        items.add(item);
    }

    /**
     * Возвращает список товаров по указанному идентификатору. Например "minecraft:brick".
     *
     * @param id Идентификатор товара.
     * @return Список товаров, который получили по переданому идентификатору.
     */
    public List<ShopItem> getItemsById(String id) {
        return itemsById.get(id);
    }

    /**
     * Возвращает товар по его названию. Например "БЕЛАЯ_ШЕРСТЬ".
     *
     * @param name Название товара.
     * @return Товар, который получил по его названию.
     */
    public ShopItem getItemByName(String name) {
        return itemsByName.get(name);
    }

}
