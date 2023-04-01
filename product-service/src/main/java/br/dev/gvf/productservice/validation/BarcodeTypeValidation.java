package br.dev.gvf.productservice.validation;

import java.util.UUID;

public interface BarcodeTypeValidation {

  /**
   * Check if barcode type name already exists in the database
   *
   * @param name name of the barcode type
   */
  void checkNewNameUnique(String name);

  /**
   * Check if barcode type name already exists in the database excluding the name of the barcodetype externalId
   *
   * @param name name of the barcode type
   * @param id external Id of the barcode type that is being updated
   */
  void checkUpdateNameUnique(String name, UUID id);

  /**
   * Check if a barcode type with the supplied id existes in the database
   * @param id external Id of the barcode type
   */
  void checkExternalIdExists(UUID id);

  /**
   * Check if a barcode type has relationships with one or more products
   * @param id
   */
  void checkHasProductRelationships(UUID id);
}
