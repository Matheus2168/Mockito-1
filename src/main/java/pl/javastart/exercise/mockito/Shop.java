package pl.javastart.exercise.mockito;

import java.util.Map;
import java.util.Set;

public class Shop {

    private int money;
    private Map<Item, Integer> stock;
    private MusicPlayer musicPlayer;

    public Shop(int money, Map<Item, Integer> stock,MusicPlayer musicPlayer) {
        this.money = money;
        this.stock = stock;
        this.musicPlayer = musicPlayer;
    }

    void playCashSound() {
        musicPlayer.playSound("sojksdf");
        /* zakładamy, że ta metoda odtwarza dźwięk https://www.youtube.com/watch?v=Wj_OmtqVLxY, nie musimy jej implementować,
        sprawdzamy tylko czy została uruchomiona */
    }

    public boolean hasItem(String itemName) {
        Set<Item> items = stock.keySet();
        for (Item item : items) {
            if (item.getName().equals(itemName)){
                if (stock.get(item) > 0){
                    return true;
                }
            }
        }
        return false;
    }

    public Item findItemByName(String itemName) {
        Set<Item> items = stock.keySet();
        for (Item item : items) {
            if (item.getName().equals(itemName)){
                return item;
            }
        }
        return null;
    }

    public void addMoney(int price){
        money+=price;
    }

    public int getMoney() {
        return money;
    }

    public Map<Item, Integer> getStock() {
        return stock;
    }


}
