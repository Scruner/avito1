package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ModeratorListDto;
import com.amr.project.model.entity.Moderator;

public interface ModeratorService {
   ModeratorListDto findAllUnModerated();
   void accept(Moderator moderator);
   void unAccept(Moderator moderator);
}
