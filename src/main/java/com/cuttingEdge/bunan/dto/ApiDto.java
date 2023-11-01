package com.cuttingEdge.bunan.dto;

import java.util.List;

public record ApiDto<T> (List<T> results){
}