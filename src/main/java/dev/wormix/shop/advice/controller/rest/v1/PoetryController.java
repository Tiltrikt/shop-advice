package dev.wormix.shop.advice.controller.rest.v1;

import dev.wormix.shop.advice.service.PoetryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PoetryController {

    @NotNull PoetryService poetryService;

    @GetMapping("/cathaiku")
    public String generateHaiku(@NotNull @ModelAttribute MultipartFile multipartFile){
        return poetryService.parseBill(multipartFile);
    }
}