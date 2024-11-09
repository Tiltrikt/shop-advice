package dev.wormix.shop.advice.controller.rest.v1;

import dev.wormix.shop.advice.service.billing.BillParserService;
import dev.wormix.shop.advice.service.billing.BillStoringService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillUploadController {

    @NotNull BillParserService billParserService;

    @NotNull BillStoringService billStoringService;

    @GetMapping("/upload")
    public String uploadBill(@NotNull @ModelAttribute MultipartFile multipartFile){
        return billParserService.parseBill(multipartFile).toString();
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