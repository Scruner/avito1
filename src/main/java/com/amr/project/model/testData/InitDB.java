package com.amr.project.model.testData;

import com.amr.project.model.entity.*;
import com.amr.project.model.enums.Gender;
import com.amr.project.model.enums.Role;
import com.amr.project.model.enums.Status;
import com.amr.project.service.abstracts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class InitDB {
    private final FeedbackService feedbackService;
    private final ReviewService reviewService;
    private final CouponService couponService;
    private final DiscountService discountService;
    private final FavoriteService favoriteService;
    private final CategoryService categoryService;
    private final CartItemService cartItemService;
    private final OrderService orderService;
    private final ItemService itemService;
    private final CountryService countryService;
    private final AddressService addressService;
    private final ShopService shopService;
    private final MessageService messageService;
    private final ChatService chatService;
    private final UserService userService;
    private final UserInfoService userInfoService;

    @Autowired
    public InitDB(
            FeedbackService feedbackService, UserService userService,
            ShopService shopService, ReviewService reviewService,
            OrderService orderService, MessageService messageService,
            ItemService itemService, UserInfoService userInfoService,
            FavoriteService favoriteService, DiscountService discountService,
            CouponService couponService, CountryService countryService,
            ChatService chatService,CategoryService categoryService,
            CartItemService cartItemService,AddressService addressService) {
        this.cartItemService = cartItemService;
        this.couponService = couponService;
        this.discountService = discountService;
        this.favoriteService = favoriteService;
        this.feedbackService = feedbackService;
        this.orderService = orderService;
        this.reviewService = reviewService;
        this.userService = userService;
        this.shopService = shopService;
        this.messageService = messageService;
        this.countryService = countryService;
        this.chatService = chatService;
        this.addressService = addressService;
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.userInfoService = userInfoService;
    }

    @PostConstruct
    public void addressPostConstruct() throws IOException {
        User user1 = User.builder()
                .email("user1@mail.ru").username("nik1")
                .password("qwerty1").activate(true)
                .activationCode("133").phone("19458764531")
                .firstName("Николай").lastName("Степанов")
                .age(27).image(createImage("users/user1", true))
                .birthday(Calendar.getInstance())
                .role(Role.ADMIN).gender(Gender.MALE)
                .build();
        userService.persist(user1);

        User user2 = User.builder()
                .email("user2@mail.ru").username("nik2")
                .password("qwerty2").activate(true)
                .activationCode("233").phone("29458764532")
                .firstName("Иван").lastName("Петров")
                .age(23).image(createImage("users/user2", true))
                .role(Role.USER).gender(Gender.FEMALE)
                .build();
        userService.persist(user2);

        User user3 = User.builder()
                .email("user3@mail.ru").username("nik3")
                .password("qwerty3").activate(true)
                .activationCode("333").phone("39458764533")
                .firstName("Роман").lastName("Иванов")
                .age(47).image(createImage("users/user3", true))
                .role(Role.MODERATOR).birthday(Calendar.getInstance())
                .build();
        userService.persist(user3);

        Address address1 = Address.builder()
                .cityIndex("123456").user(user1)
                .city(City.builder()
                        .name("Москва")
                        .country(Country.builder().name("Россия").build())
                        .build())
                .street("ул.Ленина").house("3")
                .build();
        addressService.persist(address1);

        Address address2 = Address.builder()
                .cityIndex("234567").user(user2)
                .city(City.builder()
                        .name("Владивосток")
                        .country(Country.builder()
                                .name("Франция")
                                .build())
                        .build())
                .street("ул.Ломоносова").house("4")
                .build();
        addressService.persist(address2);

        Address address3 = Address.builder()
                .cityIndex("345678").user(user3)
                .city(City.builder()
                        .name("Москва")
                        .country(Country.builder()
                                .name("Россия")
                                .build())
                        .build())
                .street("Ленинградский пр-т")
                .house("5")
                .build();
        addressService.persist(address3);

        Shop shop1 = Shop.builder()
                .name("Kenzo").email("kenzo@mail.ru")
                .phone("84953467111").description("good magazine1")
                .countryId(countryService.findAll().get(0).getId())
                .logo(createImage("shops/shop1", true))
                .count(5).rating(5.1).user(user2).isModerated(true)
                .isModerateAccept(false).moderatedRejectReason("no good1")
                .isPretendentToBeDeleted(true)
                .build();
        shopService.persist(shop1);

        Shop shop2 = Shop.builder()
                .name("Menzo").email("menzo@mail.ru")
                .phone("84953467222").description("good magazine2")
                .countryId(countryService.findAll().get(1).getId())
                .logo(createImage("shops/shop2", true))
                .count(6).rating(5.2).user(user1)
                .isModerated(true).isModerateAccept(true)
                .moderatedRejectReason("no good2").isPretendentToBeDeleted(true)
                .build();
        shopService.persist(shop2);

        Shop shop3 = Shop.builder()
                .name("Shizo").email("shizo@mail.ru")
                .phone("84953467333").description("good magazine3")
                .countryId(countryService.findAll().get(2).getId())
                .logo(createImage("shops/shop3", true))
                .count(7).rating(5.3).user(user1)
                .isModerated(false).isModerateAccept(true)
                .moderatedRejectReason("no good3").isPretendentToBeDeleted(false)
                .build();
        shopService.persist(shop3);

        persistCategory("Notebooks");
        persistCategory("Guitar");
        persistCategory("Console");

        Item item1 = Item.builder()
                .name("macbook").basePrice(BigDecimal.valueOf(151))
                .price(BigDecimal.valueOf(251)).count(31)
                .categoryId(categoryService.findAll().get(0).getId())
                .rating(5.1).description("very good1")
                .discount(501).shop(shop1)
                .isModerated(true).moderatedRejectReason("very bad1")
                .isPretendedToBeDeleted(true)
                .images(Stream.of(createImage("items/macbook", false)).collect(Collectors.toList()))
                .build();
        itemService.persist(item1);

        Item item2 = Item.builder()
                .name("PS5").basePrice(BigDecimal.valueOf(152))
                .price(BigDecimal.valueOf(252)).count(32)
                .categoryId(categoryService.findAll().get(1).getId())
                .rating(5.2).description("very good2")
                .discount(502).shop(shop2)
                .isModerated(false).moderatedRejectReason("very bad2")
                .isPretendedToBeDeleted(false)
                .images(Stream.of(createImage("items/ps5", true)).collect(Collectors.toList()))
                .build();
        itemService.persist(item2);

        Item item3 = Item.builder()
                .name("Guitar").basePrice(BigDecimal.valueOf(153))
                .price(BigDecimal.valueOf(253)).count(33)
                .categoryId(categoryService.findAll().get(2).getId())
                .rating(5.3).description("very good3")
                .discount(503).shop(shop3)
                .isModerated(true).moderatedRejectReason("very bad3")
                .isPretendedToBeDeleted(false)
                .images(Stream.of(createImage("items/guitar", true)).collect(Collectors.toList()))
                .build();
        itemService.persist(item3);

        persistOrder(Status.START,451.99,userService.findAll().get(0), item1, item3);
        persistOrder(Status.COMPLETE,452.12,userService.findAll().get(1), item2);
        persistOrder(Status.WAITING,453.00,userService.findAll().get(2), item1, item3);

        persistChat(user1, user2);
        persistChat(user3, user1);
        persistChat(user2, user3);

        persistMessage(user1, user2, true, "hello", chatService.findAll().get(0));
        persistMessage(user1, user3, false, "how price?", chatService.findAll().get(1));
        persistMessage(user2, user1, true, "hello u too", chatService.findAll().get(0));

        persistCartItem(41,user1, item3);
        persistCartItem(24,user2, item3);
        persistCartItem(32,user2, item1);

        persistFavorite(item2,user3);
        persistFavorite(item1,user2);
        persistFavorite(item3,user1);

        persistDiscount(4, 15, shop1, user2);
        persistDiscount(1, 35, shop3, user3);
        persistDiscount(10, 25, shop2, user1);

        persistCoupon(503, 53, shop2, user2);
        persistCoupon(1501, 250, shop1, user3);
        persistCoupon(2502, 10, shop3, user1);

        persistReview("brand", "price, material", "awesome", 1, user3, item2, false, false, "bad is bad");
        persistReview("price", "material", "badly", 2, user1, item2, true, true, "nice");
        persistReview("none", "price", "cryyyyyyyy", 3, user3, item3, true, false, "negative");

        persistFeedback("Отличный товар", user2, "Не сломался");
        persistFeedback("НЕ покупать, ломается", user3, "cломался");
        persistFeedback("норм", user1, "просто");

        persistUserInfo(user1);
        persistUserInfo(user2);
        persistUserInfo(user3);

    }

    private Image createImage(String localPath, Boolean isMain) throws IOException {
        return Image.builder()
                .picture(Files.readAllBytes(
                        new File(getClass().getResource("/images/" + localPath + ".jpg").getFile()).toPath())
                )
                .isMain(isMain)
                .build();
    }

    private void persistOrder(Status status, Double total, User user, Item ...item) {
        Order order = Order.builder()
                .itemsId(Arrays.stream(item).map(i -> i.getId()).collect(Collectors.toList()))
                .date(Calendar.getInstance())
                .status(status)
                .address(user.getAddress().get(0).getId())
                .total(BigDecimal.valueOf(total))
                .user(user)
                .buyerName(user.getFirstName())
                .buyerPhone(user.getPhone())
                .build();
        orderService.persist(order);
    }

    private void persistReview(String dignity, String flaw, String text, int rating, User user, Item item,
                               Boolean isModerated, Boolean isModeratedAccept, String reason) {
        Review review =
                Review.builder()
                        .dignity(dignity)
                        .flaw(flaw)
                        .text(text)
                        .date(Calendar.getInstance().getTime())
                        .rating(rating)
                        .nameUser(user.getUsername())
                        .item(item)
                        .isModerated(isModerated)
                        .isModerateAccept(isModeratedAccept)
                        .moderatedRejectReason(reason)
                        .build();
        reviewService.persist(review);
    }

    private void persistMessage(User to, User from, Boolean viewed, String text, Chat chat) {
        Message msg = Message.builder()
                .to(to)
                .from(from)
                .viewed(viewed)
                .textMessage(text)
                .chat(chat)
                .build();
        messageService.persist(msg);
    }

    private void persistChat(User user1, User user2) {
        Chat chat = Chat.builder()
                .members(Stream.of(user1, user2)
                        .collect(Collectors.toList()))
                .build();
        chatService.persist(chat);
    }

    private void persistCategory(String name) {
        categoryService.persist(Category.builder().name(name).build());
    }

    private void persistCartItem(int quantity, User user, Item item) {
        CartItem cartItem = CartItem.builder()
                .quantity(quantity)
                .user(user)
                .item(item)
                .build();
        cartItemService.persist(cartItem);
    }

    private void persistFavorite(Item item, User user) {
        Favorite favorite = Favorite.builder()
                .itemId(item.getId())
                .shopId(item.getShop().getId())
                .user(user)
                .build();
        favoriteService.persist(favorite);
    }

    private void persistDiscount(int minOrder, int percentage, Shop shop, User user) {
        Discount discount = Discount.builder()
                .minOrder(minOrder)
                .percentage(percentage)
                .user(user)
                .shop(shop)
                .build();
        discountService.persist(discount);
    }

    private void persistCoupon(int sum, int minOrder, Shop shop, User user) {
        Coupon coupon = Coupon.builder()
                .sum(sum)
                .minOrder(minOrder)
                .shopId(shop.getId())
                .user(user).build();
        couponService.persist(coupon);
    }

    private void persistUserInfo(User user) {
        UserInfo userInfo = UserInfo.builder()
                .user(user).age(user.getAge())
                .birthday(user.getBirthday())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .gender(user.getGender())
                .build();
        userInfoService.persist(userInfo);
    }

    private void persistFeedback(String text, User user, String reason) {
        Feedback feedback = Feedback.builder()
                .dateTime(LocalDateTime.now())
                .fullText(text)
                .username(user.getUsername())
                .reason(reason)
                .build();
        feedbackService.persist(feedback);
    }
}
