package dev.wormix.shop.advice.configuration.ai;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BillSchema(
  @JsonProperty(required = true, value = "total_price") double totalPrice,
  @JsonProperty(required = true, value = "organization_name") String organizationName,
  @JsonProperty(required = true, value = "items") Item[] items) {

  public record Item(
    @JsonProperty(required = true, value = "name") String name,
    @JsonProperty(required = true, value = "unit_price") double unitPrice,
    @JsonProperty(required = true, value = "quantity") int quantity
  ) {}
//    @JsonProperty(required = true, value = "category") Category category) {

//    public record Category(
//      @JsonProperty(required = true, value = "product_group_eng") String productGroupEng,
//      @JsonProperty(required = true, value = "product_name_eng") String productNameEng) {
//    }
//  }
}

//public static final String RESPONSE_SCHEMA = """
//      {
//        "type": "object",
//        "properties": {
//        "total_price": { "type": "number" },
//        "organization_name": { "type": "string" },
//        "items": {
//          "type": "array",
//            "items": {
//            "type": "object",
//              "properties": {
//              "name": { "type": "string" },
//              "unit_price": { "type": "number" },
//              "quantity": { "type": "number" },
//              "category": {
//                "type": "object",
//                  "properties": {
//                  "product_group_eng": { "type": "string" },
//                  "product_name_eng": { "type": "string" }
//                },
//                "required": ["product_group_eng", "product_name_eng"],
//                "additionalProperties": false
//              }
//            },
//            "required": ["name", "unit_price", "quantity", "category"],
//            "additionalProperties": false
//          }
//        }
//      },
//        "required": ["total_price", "organization_name", "items"],
//        "additionalProperties": false
//      }
//    """;
