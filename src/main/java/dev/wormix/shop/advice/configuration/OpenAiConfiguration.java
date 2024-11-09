package dev.wormix.shop.advice.configuration;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.ResponseFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfiguration {
  //  "line_items": [
//    {
//      "category": "pečivo, chléb, koláče",
//      "description": "K.Mléčné houstičky",
//      "discount": null,
//      "order": 9,
//      "price": 45.90,
//      "quantity": 1,
//      "total": 45.90,
//      "type": "product"
//    },
//  {
//    "type": "object",
//    "properties": {
//    "total_price": { "type": "number" },
//    "organization_name": { "type": "string" },
//    "items": {
//      "type": "array",
//        "items": {
//        "type": "object",
//          "properties": {
//          "name": { "type": "string" },
//          "unit_price": { "type": "number" },
//          "quantity": { "type": "number" },
//          "category": {
//            "type": "object",
//              "properties": {
//              "product_group_eng": { "type": "string" },
//              "product_name_eng": { "type": "string" }
//            },
//            "required": ["product_group_eng", "product_name_eng"],
//            "additionalProperties": false
//          }
//        },
//        "required": ["name", "unit_price", "quantity", "category"],
//        "additionalProperties": false
//      }
//    }
//  },
//    "required": ["total_price", "organization_name"],
//    "additionalProperties": false
//  }\

  // TO USER RECEIPTS
public static final String RESPONSE_SCHEMA = """
  {
    "type": "object",
    "properties": {
    "total_price": { "type": "number" },
    "organization_name": { "type": "string" },
    "items": {
      "type": "array",
        "items": {
        "type": "object",
          "properties": {
          "name": { "type": "string" },
          "unit_price": { "type": "number" },
          "quantity": { "type": "number" },
          "category": {
            "type": "object",
              "properties": {
              "product_group_eng": { "type": "string" },
              "product_name_eng": { "type": "string" }
            },
            "required": ["product_group_eng", "product_name_eng"],
            "additionalProperties": false
          }
        },
        "required": ["name", "unit_price", "quantity", "category"],
        "additionalProperties": false
      }
    }
  },
    "required": ["total_price", "organization_name", "items"],
    "additionalProperties": false
  }
""";

  @Bean
  public OpenAiChatModel openAiChatModel() {
    System.out.println(System.getenv("OPENAI_API_KEY"));
    var openAiApi = new OpenAiApi(System.getenv("OPENAI_API_KEY"));
    var options = OpenAiChatOptions.builder()
      .withModel(OpenAiApi.ChatModel.GPT_4_O_MINI.getValue())
      .withResponseFormat(new ResponseFormat(ResponseFormat.Type.JSON_SCHEMA, OpenAiConfiguration.RESPONSE_SCHEMA))
      .build();
    return new OpenAiChatModel(openAiApi, options);
  }
}
