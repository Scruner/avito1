package com.amr.project.converter;

import com.amr.project.model.entity.Moderator;
import org.mapstruct.Mapper;
import java.util.Map;

@Mapper(componentModel = "spring", uses = {ShopMapper.class, ItemMapper.class, ReviewMapper.class})
public interface ModeratorMapper {

    default Moderator toEntity(Map<String, Map<String, ?>> entity) {
        Moderator moderator = new Moderator();
        for(Map.Entry<String, Map<String,?>> entry : entity.entrySet()) {
            moderator.setType(entry.getKey().substring(0,1).toUpperCase() + entry.getKey().substring(1));
            for (Map.Entry<String,?> valueEntry : entry.getValue().entrySet()) {
                if (valueEntry.getKey().contains("id")) {
                    moderator.setId(Long.valueOf(valueEntry.getValue().toString()));
                } else {
                    moderator.setReason(valueEntry.getValue().toString());
                }
            }
        }
        return moderator;
    };
}
