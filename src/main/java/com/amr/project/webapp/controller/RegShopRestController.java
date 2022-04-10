package com.amr.project.webapp.controller;

import com.amr.project.converter.ShopMapper;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/regshop")

public class RegShopRestController {
    private final ShopService shopService;
    private final UserService userService;
    private final ShopMapper shopMapper;

    @PostMapping
    // метод регистрации магазина
    public ResponseEntity<ShopDto> RegShop(@RequestBody ShopDto shop) {
        if (shop.isModerated() == true) shop.setModerateAccept(true);
        else if (shop.isModerated() == false) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Shop shopReg = shopService.persist(shopMapper.toEntity(shop));
        return ResponseEntity.ok(shopMapper.toDto(shopReg));
    }

    @DeleteMapping("/UnReg/{id}")
    // метод удаления магазина модератором из списка зарегистрированных
    public ResponseEntity<Void> UnRegShop(@PathVariable Long id) {
        shopService.deleteByIdCascadeEnable(id);
        return new ResponseEntity(HttpStatus.OK);
    }

       @DeleteMapping("/pretendentDelShop")
    // метод постановки магазина в очередь на удаление
    public ResponseEntity<ShopDto> PretendentDelShop(@RequestBody ShopDto shop) {
        shop.setPretendentToBeDeleted(true);
        Shop shopPretDel = shopService.persist(shopMapper.toEntity(shop));
        return ResponseEntity.ok(shopMapper.toDto(shopPretDel));
    }


    @GetMapping("/{id}")
    // получение магазина по id
    public ResponseEntity<ShopDto> getShopDtoById(@PathVariable Long id) {
        Shop shop = shopService.findById(id);
        return ResponseEntity.ok(shopMapper.toDto(shop));
    }

    @PutMapping
    // метод редактирования магазина
    public ResponseEntity<ShopDto> updateShop(@RequestBody ShopDto shopDto) {
        Shop shop = shopMapper.toEntity(shopDto);
        shopService.update(shop);
        Shop shopUpdate = shopService.findById(shop.getId());
        return ResponseEntity.ok(shopMapper.toDto(shopUpdate));
    }

    @GetMapping
    // получение всех зарегистрированных магазинов
    public ResponseEntity<List<ShopDto>> getAllShop() {
        return ResponseEntity.ok(shopMapper.toDtoList(shopService.findAll()));
    }

    @GetMapping (path="/getListShop")
    // получение cписка магазинов в зависимости от значения поля isModerated
    public ResponseEntity<List<ShopDto>> getListShopbyisModerated(@PathVariable boolean isModerated) {
        List<ShopDto> allshops = shopMapper.toDtoList(shopService.findAll());
        List<ShopDto> allListshops = new ArrayList<>();
        for (ShopDto sh : allshops) {
            if (sh.isModerated()) {
                allListshops.add(sh);
            }
        }
        return ResponseEntity.ok(allListshops);
    }
}
