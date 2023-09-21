package com.CuttingEdge.bunan.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.description.type.TypeList;

import java.util.List;

public record ApiDto<T> (List<T> results){
}