package com.amr.project.webapp.controller;

import com.amr.project.converter.ShopMapper;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/regshop")

public class RegShopRestController {
    private final ShopService shopService;
    private final ShopMapper shopMapper;

    @PostMapping
    public ResponseEntity<ShopDto> RegShop(@RequestBody ShopDto shop) {
        if (shop.isModerated() == true) shop.setModerateAccept(true);
        else if (shop.isModerated() == false) {
            System.out.println("Данные магазина не прошли проверку модератором");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Shop shopReg = shopService.persist(shopMapper.toEntity(shop));
        return ResponseEntity.ok(shopMapper.toDto(shopReg));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> UnRegShop(@PathVariable Long id, ShopDto shop) {
        shopService.deleteByIdCascadeEnable(id);
        shop.setModerated(false);
        shop.setModerateAccept(false);
        System.out.println("Магазин исключен из списка зарегистрированных");
        return new ResponseEntity(HttpStatus.OK);
    }
}
