package dev.wormix.shop.advice.controller.rest.v1;

import dev.wormix.shop.advice.service.billing.BillParserService;
import dev.wormix.shop.advice.service.billing.BillStoreService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillUploadController {

    @NotNull BillParserService billParserService;

    @NotNull BillStoreService billStoreService;

    @GetMapping("/upload")
    public String uploadBill(@NotNull @ModelAttribute MultipartFile multipartFile){
        log.info("Entering upload zone");
        var bill = billParserService.parseBill(multipartFile);
        log.info(bill.toString());
        billStoreService.store(bill);

        return bill.toString();
    }

    @GetMapping("/testp")
    public String testp(){
        return billParserService.testParser();
    }

    @GetMapping("/testc")
    public String testc(@RequestParam String text){
        return billParserService.testClass(text);
    }

}