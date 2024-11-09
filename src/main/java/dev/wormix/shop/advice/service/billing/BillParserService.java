package dev.wormix.shop.advice.service.billing;

import dev.wormix.shop.advice.configuration.ai.BillSchema;
import dev.wormix.shop.advice.configuration.ai.ClassSchema;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.model.Media;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillParserService {

  public static final String PARSE_BILL = """
      Распарси текстовый чек в JSON формат с использованием следующей структуры данных:
      
      currency_code: строка, код валюты, например, "CZK".
      date: строка, дата покупки, если присутствует.
      default_category: строка, стандартная категория.
      delivery_date: строка, дата доставки, если есть.
      delivery_note_number: строка, номер накладной.
      discount: сумма скидки, если есть.
      document_reference_number: строка, номер документа.
      document_title: строка, название документа.
      document_type: строка, тип документа (например, 'receipt').
      due_date: строка, дата оплаты, если есть.
      duplicate_of: строка, ID дубликата, если есть.
      exch_rate: дробное число, курс валюты.
      external_id: строка, внешний ID документа.
      final_balance: строка, конечный баланс, если присутствует.
      guest_count: целое число, количество гостей, если применимо.
      id: целое число, уникальный идентификатор документа.
      img_blur: логическое значение, указывает на наличие размытия в изображении.
      insurance: строка, информация о страховке, если есть.
      invoice_number: строка, номер счета-фактуры.
      is_approved: логическое значение, указывает на подтверждение документа.
      is_blurry: массив логических значений, указывающих на наличие размытия.
      is_document: логическое значение, указывает на наличие документа.
      is_duplicate: логическое значение, указывает на дубликат документа.
      is_money_in: логическое значение, указывает на приходные операции.
      license_plate_number: строка, номерной знак транспортного средства, если есть.
      line_items: массив объектов, представляющих позиции в чеке. Каждый объект имеет следующие поля:
      category: строка, категория позиции.
      country_of_origin: строка, страна происхождения, если есть.
      custom_fields: объект, содержащий дополнительные поля для позиции.
      date: строка, дата позиции, если применимо.
      description: строка, краткое описание позиции.
      discount: сумма скидки на позицию, если есть.
      discount_rate: дробное число, процент скидки, если есть.
      end_date: строка, дата окончания действия позиции, если применимо.
      full_description: строка, полное описание позиции.
      hsn: строка, код позиции HSN, если есть.
      id: целое число, уникальный идентификатор позиции.
      lot: строка, номер партии, если есть.
      manufacturer: строка, производитель, если есть.
      normalized_description: строка, нормализованное описание позиции.
      order: целое число, порядок позиции.
      price: дробное число, цена за единицу.
      quantity: целое число, количество.
      reference: строка, ссылка, если есть.
      section: строка, секция позиции, например, 'drogerie'.
      sku: строка, код SKU, если есть.
      start_date: строка, дата начала действия позиции.
      tags: массив строк, теги позиции, если есть.
      tax: сумма налога на позицию, если есть.
      tax_code: строка, код налога.
      tax_rate: дробное число, ставка налога.
      text: строка, текстовое описание позиции.
      total: дробное число, общая сумма позиции.
      type: строка, тип позиции (например, 'product' или 'food').
      unit_of_measure: строка, единица измерения, если есть.
      upc: строка, код UPC, если есть.
      weight: строка, вес позиции, если есть.
      organization_name: single word""";


  @NotNull ChatModel parser;

  @NotNull ChatModel classifier;

  @SneakyThrows
  public Bill parseBill(@NotNull MultipartFile multipartFile) {
    Resource imageResource = new ByteArrayResource(multipartFile.getBytes());
    Media photoMedia = new Media(MediaType.IMAGE_JPEG, imageResource);
    UserMessage userMessage = new UserMessage(PARSE_BILL, photoMedia);

    var content = parser.call(userMessage);
    var outputConverter = new BeanOutputConverter<>(BillSchema.class);
    var bill = outputConverter.convert(content);

    return Bill.builder()
        .totalPrice(bill.totalPrice())
        .organizationName(bill.organizationName())
        .items(classifyItems(bill)).build();
  }

  private List<ClassifiedItem> classifyItems(BillSchema bill) {
    var classifiedItems = new ArrayList<ClassifiedItem>();
    var outputConverter = new BeanOutputConverter<>(ClassSchema.class);

    for (var item : bill.items()) {
      String clazz = outputConverter
          .convert(classifier.call(item.name()))
          .clazz();

      classifiedItems.add(ClassifiedItem.builder()
          .name(item.name())
          .unitPrice(item.unitPrice())
          .quantity(item.quantity())
          .clazz(clazz)
          .build());
    }

    return classifiedItems;
  }

  public String testClass(String text) {
    System.out.println(classifier.call("Šmak majon.400ml"));
    System.out.println(text);
    return classifier.call(text);
  }

  public String testParser() {
    return parser.call("Šmak majon.400ml");
  }

}