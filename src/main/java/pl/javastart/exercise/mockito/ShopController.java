package pl.javastart.exercise.mockito;

public class ShopController {

    private Shop shop;

    public ShopController(ShopRepository shopRepository) {
        shop = shopRepository.findShop();

    }

    public void sellItem(Human human, String itemName) {

        if (shop.hasItem(itemName)) {
            Item item = shop.findItemByName(itemName);
            if (item.getAgeRestriction() > human.getAge()) {
                throw new TooYoungException();
            }
            else if (human.getMoney() < item.getPrice()){
                throw new NotEnaughMoneyException();
            }
            else if (human.getJob().equals("Policjant") && !item.isLegal()){
                throw new BagietaException();
            }
            else {
                human.setMoney(human.getMoney()-item.getPrice());
                shop.addMoney(item.getPrice());
                shop.getStock().replace(item,shop.getStock().get(item)-1);
                if (shop.getStock().get(item) == 0){
                    shop.getStock().remove(item);
                }
                shop.playCashSound();

            }

        } else {
            throw new OutOfStockException();
        }

    }

}
