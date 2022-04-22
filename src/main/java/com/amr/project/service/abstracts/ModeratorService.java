package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ModeratorListDto;
import com.amr.project.model.entity.Moderator;
import org.springframework.security.access.prepost.PreAuthorize;

public interface ModeratorService {
   @PreAuthorize("hasRole('ROLE_MODERATOR')")
   ModeratorListDto findAllUnModerated();
   @PreAuthorize("hasRole('ROLE_MODERATOR')")
   void accept(Moderator moderator);
   @PreAuthorize("hasRole('ROLE_MODERATOR')")
   void unAccept(Moderator moderator);
}
