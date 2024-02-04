package com.example.libraryback.payload.offer;

import com.example.libraryback.payload.book.BookDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferDTO {

    private UUID id;

    private BookDTO book;

    private UUID bkgImage;

}
