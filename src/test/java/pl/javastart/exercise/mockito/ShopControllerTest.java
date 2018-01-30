package pl.javastart.exercise.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShopControllerTest {

    @Mock
    ShopRepository shopRepository;


    private ShopController shopController;
    private Shop shop;
    private MusicPlayer musicPlayer;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        musicPlayer = mock(MusicPlayer.class);

        Map<Item, Integer> stock = new HashMap<>();
        stock.put(new Item("Piwo", 18, 4, true), 5);
        stock.put(new Item("Bitcoin",18,30000,true),1);
        stock.put(new Item("Cukierek",1,2,true),1);

        shop = new Shop(0, stock,musicPlayer);

        when(shopRepository.findShop()).thenReturn(shop);

        shopController = new ShopController(shopRepository);
    }

    @Test(expected = TooYoungException.class)
    public void shouldNotSellBeerToYoungling() {
        // given
        Human human = new Human("Janek",16,"Sprzedawca",100);

        // when
        shopController.sellItem(human, "Piwo");
    }

    @Test(expected = NotEnaughMoneyException.class)
    public void shouldNotSellIfNotEnaughMoney(){
        //given
        Human human = new Human("Marek",20,"Kierowca",2000);

        //when
        shopController.sellItem(human,"Bitcoin");
    }

    @Test(expected = OutOfStockException.class)
    public void shouldNotSellIfOutOfStock(){
        //given
        Human human = new Human("Marek",20,"Kierowca",2000);

        //when
        shopController.sellItem(human,"Cukierek");
        shopController.sellItem(human,"Cukierek");
    }

    @Test
    public void shouldPlaySound(){
        //given
        Human human = new Human("Bartek",25,"Wariat",300);

        //when
        shopController.sellItem(human,"Cukierek");

        verify(musicPlayer).playSound(anyString());


    }

}
