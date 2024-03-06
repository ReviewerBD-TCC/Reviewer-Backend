package com.reviewer.reviewer.dto.forms;

import com.reviewer.reviewer.models.IndicatedUsers;
import com.reviewer.reviewer.models.User;

public record IndicatedUserDto(
        Long id,
        Long user_id
) {
    public IndicatedUserDto(IndicatedUsers indicatedUsers) {
        this(indicatedUsers.getId(), indicatedUsers.getUserIndicated().getId());
    }
}
