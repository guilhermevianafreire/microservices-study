package br.dev.gvf.productservice.dto.barcodetype.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class BarcodeTypeResponse {

    private UUID id;
    private String name;

}
