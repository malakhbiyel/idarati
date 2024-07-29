package com.mrafp.idarati.Mapper;

import com.mrafp.idarati.Dto.ConditionDto;
import com.mrafp.idarati.Model.Condition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConditionMapper {

    ConditionDto toDto(Condition condition);
    Condition toEntity(ConditionDto conditionDto);
}
