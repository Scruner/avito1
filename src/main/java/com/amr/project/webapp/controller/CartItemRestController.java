package com.amr.project.webapp.controller;

import com.amr.project.converter.CartItemMapper;
import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartItemRestController {

    private final CartItemService cartItemService;
    private final CartItemMapper cartItemMapper;

    @Autowired
    public CartItemRestController(CartItemService cartItemService, CartItemMapper cartItemMapper) {
        this.cartItemService = cartItemService;
        this.cartItemMapper = cartItemMapper;

    }

    @GetMapping()
    public ResponseEntity<List<CartItemDto>> getBasket (@AuthenticationPrincipal User user)  {
        return user == null ?
                new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED)
                :
                ResponseEntity.ok(cartItemMapper.toDtoList(cartItemService.findByUser(user.getId())));
    }

    @PostMapping()
    public ResponseEntity<List<CartItemDto>> saveBasket (@AuthenticationPrincipal User user, @RequestBody List<CartItemDto> cartItems) {
        if (user == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
        } else if (cartItems.isEmpty()) {
            return new ResponseEntity<>(cartItemMapper.toDtoList(cartItemService.findByUser(user.getId())), HttpStatus.OK);
        }
        cartItemMapper.toEntityList(cartItems)
                .forEach(cartItemService::persist);
        return new ResponseEntity<>(cartItemMapper.toDtoList(cartItemService.findByUser(user.getId())), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<List<CartItemDto>> updateBasket (@AuthenticationPrincipal User user, @RequestBody List<CartItemDto> cartItems) {
        if (user == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
        } else if (cartItems == null) {
            return new ResponseEntity<>(cartItemMapper.toDtoList(cartItemService.findByUser(user.getId())), HttpStatus.BAD_REQUEST);
        }

        cartItemMapper.toEntityList(cartItems)
                .forEach(cartItemService::update);

        return ResponseEntity.ok(cartItemMapper.toDtoList(cartItemService.findByUser(user.getId())));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteBasket (@AuthenticationPrincipal User user, @RequestBody List<CartItemDto> cartItems) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (cartItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        cartItemMapper.toEntityList(cartItems)
                .forEach(cartItemService::delete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
