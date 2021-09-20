package com.julien.sportapi.dto.general;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@AllArgsConstructor
@Data
public class UuId {
    @NonNull
    private UUID id;
}
