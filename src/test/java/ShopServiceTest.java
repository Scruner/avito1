import com.amr.project.dao.abstracts.ShopReadWriteDao;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.impl.ShopServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShopServiceTest {

    @Mock
    private ShopReadWriteDao dao;

    @Autowired
    @InjectMocks
    private ShopServiceImpl shopService;

    @Mock
    private Shop shop1, shop2;

    private List<Shop> shopList;

    @BeforeEach
    public void setUp() {
        shopList = new ArrayList<>();

        shop1 = new Shop();
        shop1.setId(1L);
        shop1.setPhone("89111111111");

        shop2 = new Shop();
        shop2.setId(2L);
        shop2.setPhone("89177777777");

        shopList.add(shop1);
        shopList.add(shop2);

    }

    @AfterEach
    public void reSet() {
        shop1 = shop2 = null;
        shopList = null;
    }

/*    @Test
    public void addShopTest() throws Exception{
         when(dao.persist(shop1)).thenReturn(shop1);

         shopService.persist(shop1);
         verify(dao,times(1)).persist(any());
    }*/

    @Test
    public void getAllShopsTest() throws Exception {
        dao.persist(shop1);

        when(dao.findAll()).thenReturn(shopList);
        List<Shop> shopList1 = shopService.findAll();
        assertEquals(shopList1, shopList);
        verify(dao, times(1)).persist(shop1);
        verify(dao, times(1)).findAll();
    }


}
