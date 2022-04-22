package com.amr.project.webapp.controller;

import com.amr.project.converter.ModeratorMapper;
import com.amr.project.model.dto.ModeratorListDto;
import com.amr.project.model.entity.Moderator;
import com.amr.project.service.abstracts.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/moder")
public class ModeratorRestController {

    private final ModeratorMapper moderatorMapper;
    private final ModeratorService moderatorService;

    @Autowired
    public ModeratorRestController(ModeratorService moderatorService, ModeratorMapper moderatorMapper) {
        this.moderatorMapper = moderatorMapper;
        this.moderatorService = moderatorService;
    }

    @GetMapping()
    public ResponseEntity<ModeratorListDto> getAllUnModerated() {
        return ResponseEntity.ok(moderatorService.findAllUnModerated());
    }

    @PutMapping
    public ResponseEntity<Void> moderatorAccept(@RequestBody Map<String, Map<String, ?>> entity) {
        Moderator moder = moderatorMapper.toEntity(entity);
        if (moder.getReason() == null) {
            moderatorService.accept(moder);
        } else {
            moderatorService.unAccept(moder);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
