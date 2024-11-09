package dev.wormix.shop.advice.configuration.ai;

import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.ResponseFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfiguration {

  @Bean
  public OpenAiChatModel parser() {
    var outputConverter = new BeanOutputConverter<>(BillSchema.class);
    var jsonSchema = outputConverter.getJsonSchema();
    System.out.println(jsonSchema);
    var openAiApi = new OpenAiApi(System.getenv("OPENAI_API_KEY"));
    var options = OpenAiChatOptions.builder()
        .withModel(OpenAiApi.ChatModel.GPT_4_O.getValue())
        .withResponseFormat(new ResponseFormat(ResponseFormat.Type.JSON_SCHEMA, jsonSchema))
        .build();

    return new OpenAiChatModel(openAiApi, options);
  }

  @Bean
  public OpenAiChatModel classifier() {
    var outputConverter = new BeanOutputConverter<>(ClassSchema.class);
    var jsonSchema = outputConverter.getJsonSchema();
    System.out.println(jsonSchema);
    var openAiApi = new OpenAiApi(System.getenv("OPENAI_API_KEY"));
    var options = OpenAiChatOptions.builder()
        .withModel("ft:gpt-4o-mini-2024-07-18:personal:product-categorization:ARUpHk6S")
        .withResponseFormat(new ResponseFormat(ResponseFormat.Type.JSON_SCHEMA, jsonSchema))
        .build();
    return new OpenAiChatModel(openAiApi, options);
  }
}
