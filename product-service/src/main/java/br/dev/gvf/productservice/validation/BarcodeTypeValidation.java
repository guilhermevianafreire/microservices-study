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
   * @param externalId external Id of the barcode type that is being updated
   */
  void checkUpdateNameUnique(String name, UUID externalId);

  /**
   * Check if a barcode type with the supplied externalId existes in the database
   * @param externalId external Id of the barcode type
   */
  void checkExternalIdExists(UUID externalId);

}
