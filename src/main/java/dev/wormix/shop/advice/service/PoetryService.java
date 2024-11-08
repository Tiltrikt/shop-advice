package dev.wormix.shop.advice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.model.Media;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PoetryService {

    public static final String PARSE_BILL = """
        Распарси текстовый чек в JSON формат с использованием следующей структуры данных:
        
        account_number: строка, номер счета, если присутствует, иначе null.
        accounting_entry_type: строка, тип операции (например, 'debit' для списаний).
        balance: строка, баланс после операции, если присутствует, иначе null.
        barcodes: массив, содержащий строки штрих-кодов, если есть.
        bill_to: объект, содержащий реквизиты покупателя, такие как:
        address: строка, адрес покупателя, если присутствует.
        email: строка, адрес электронной почты.
        name: строка, имя покупателя.
        parsed_address: строка, адрес в отформатированном виде.
        phone_number: строка, номер телефона.
        reg_number: строка, регистрационный номер.
        vat_number: строка, номер плательщика НДС.
        cashback: сумма кэшбэка, если присутствует.
        category: строка, категория расходов, например, 'Meals & Entertainment'.
        created_date: строка в формате "YYYY-MM-DD HH:MM
        ", дата создания чека.
        currency_code: строка, код валюты, например, "CZK".
        custom_fields: объект, содержащий любые дополнительные поля, если есть.
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
        img_file_name: строка, название файла изображения.
        img_thumbnail_url: строка, URL миниатюры изображения.
        img_url: строка, URL полного изображения.
        incoterms: строка, торговый термин, если есть.
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
        weight: строка, вес позиции, если есть.""";


    @NotNull ChatModel chatModel;

    @SneakyThrows
    public String parseBill(@NotNull MultipartFile multipartFile) {
        Resource imageResource = new ByteArrayResource(multipartFile.getBytes());

        Media photoMedia = new Media(MediaType.IMAGE_JPEG, imageResource);
        UserMessage userMessage = new UserMessage(PARSE_BILL, photoMedia);
        Prompt prompt = new Prompt(userMessage);
        return chatModel.call(userMessage);
    }
}