package br.dev.gvf.productservice.controller.barcodetype;

import br.dev.gvf.productservice.constant.Roles;
import br.dev.gvf.productservice.dto.barcodetype.request.BarcodeTypeActiveRequest;
import br.dev.gvf.productservice.dto.barcodetype.request.BarcodeTypeCreateRequest;
import br.dev.gvf.productservice.dto.barcodetype.request.BarcodeTypeFilterRequest;
import br.dev.gvf.productservice.dto.barcodetype.request.BarcodeTypeUpdateRequest;
import br.dev.gvf.productservice.dto.barcodetype.response.BarcodeTypeBasePagedListResponse;
import br.dev.gvf.productservice.dto.barcodetype.response.BarcodeTypeCreateResponse;
import br.dev.gvf.productservice.dto.barcodetype.response.BarcodeTypeGetByIdResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/default")
public interface BarcodeTypeV1Controller {

  @Operation(method = "findById", summary = "Find a Barcode Type by id", description = "Return a Barcode Type by its UUID (Universally Unique Identifier)")
  @ApiResponse(responseCode = "200", description = "A Barcode Type was found with the supplied UUID (Universally Unique Identifier)", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BarcodeTypeGetByIdResponse.class)))
  @ApiResponse(responseCode = "404", description = "No Barcode Type was found with the ID supplied", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
  @ApiResponse(responseCode = "400", description = "Invalid UUID (Universally Unique Identifier) supplied", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
  @ApiResponse(responseCode = "500", description = "A internal erro has ocurred, detaisl in the error message", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @Secured({Roles.ROLE_USER, Roles.ROLE_ADMIN})
  BarcodeTypeGetByIdResponse findById(
      @Parameter(in = ParameterIn.PATH, name = "ID", description = "UUID (Universally Unique Identifier) of the Barcode Type", example = "c2e559b6-e4e3-489a-bb30-a2666fc4e1d4") @PathVariable("id") @Valid @NotNull @org.hibernate.validator.constraints.UUID(version = 4, letterCase = org.hibernate.validator.constraints.UUID.LetterCase.INSENSITIVE) UUID id);

  @Operation(method = "list", summary = "List Barcode Types", description = "List Barcode Types in a paged response with the possibility of filtering by its properties")
  @ApiResponse(responseCode = "200", description = "One or more Barcode Type were found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BarcodeTypeBasePagedListResponse.class)))
  @ApiResponse(responseCode = "500", description = "A internal erro has ocurred, details in the error message", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @Secured({Roles.ROLE_USER, Roles.ROLE_ADMIN})
  BarcodeTypeBasePagedListResponse list(@ParameterObject @Valid BarcodeTypeFilterRequest request);

  @Operation(method = "create", summary = "Create a Barcode Type", description = "Creates a Barcode Type and returns the generated UUID (Universally Unique Identifier)")
  @ApiResponse(responseCode = "201", description = "The Barcode Type was created successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BarcodeTypeBasePagedListResponse.class)))
  @ApiResponse(responseCode = "400", description = "Invalid name supplied", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
  @ApiResponse(responseCode = "500", description = "A internal erro has ocurred, details in the error message", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  @Secured(Roles.ROLE_ADMIN)
  BarcodeTypeCreateResponse create(@RequestBody @Valid BarcodeTypeCreateRequest request);

  @Operation(method = "update", summary = "Update a Barcode Type", description = "Updates a Barcode Type name")
  @ApiResponse(responseCode = "200", description = "The Barcode Type was updated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BarcodeTypeBasePagedListResponse.class)))
  @ApiResponse(responseCode = "404", description = "No Barcode Type was found with the ID supplied", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
  @ApiResponse(responseCode = "400", description = "Invalid name supplied", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
  @ApiResponse(responseCode = "400", description = "The version supplied is from a stale data", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
  @ApiResponse(responseCode = "500", description = "A internal erro has ocurred, details in the error message", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @Secured(Roles.ROLE_ADMIN)
  void update(
      @Parameter(in = ParameterIn.PATH, name = "ID", description = "UUID (Universally Unique Identifier) of the Barcode Type", example = "c2e559b6-e4e3-489a-bb30-a2666fc4e1d4") @PathVariable("id") @Valid UUID id,
      @RequestBody @Valid BarcodeTypeUpdateRequest request);

  @Operation(method = "delete", summary = "Delete a Barcode Type", description = "Delete a Barcode Type by id")
  @ApiResponse(responseCode = "200", description = "The Barcode Type was deleted successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BarcodeTypeBasePagedListResponse.class)))
  @ApiResponse(responseCode = "404", description = "No Barcode Type was found with the ID supplied", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
  @ApiResponse(responseCode = "400", description = "This BarcodeType has relationships with one or more products", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
  @ApiResponse(responseCode = "400", description = "The version supplied is form a stale data", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
  @ApiResponse(responseCode = "500", description = "A internal erro has ocurred, details in the error message", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Secured(Roles.ROLE_ADMIN)
  void delete(
      @Parameter(in = ParameterIn.PATH, name = "ID", description = "UUID (Universally Unique Identifier) of the Barcode Type", example = "c2e559b6-e4e3-489a-bb30-a2666fc4e1d4") @PathVariable("id") @Valid UUID id);

  @Operation(method = "changeActive", summary = "Change the active property", description = "Activate or deactivate a Barcode Type")
  @ApiResponse(responseCode = "200", description = "The Barcode Type active property was updated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BarcodeTypeBasePagedListResponse.class)))
  @ApiResponse(responseCode = "404", description = "No Barcode Type was found with the ID supplied", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
  @ApiResponse(responseCode = "400", description = "Failed to read request. Invalid active value suppied", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
  @ApiResponse(responseCode = "500", description = "A internal erro has ocurred, details in the error message", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
  @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @Secured(Roles.ROLE_ADMIN)
  void changeActive(
      @Parameter(in = ParameterIn.PATH, name = "ID", description = "UUID (Universally Unique Identifier) of the Barcode Type", example = "c2e559b6-e4e3-489a-bb30-a2666fc4e1d4") @PathVariable("id") @Valid UUID id,
      @Valid @RequestBody BarcodeTypeActiveRequest request);

}
