package com.entrytest.translationprogram.controller;

import com.entrytest.translationprogram.model.Translation;
import com.entrytest.translationprogram.service.TranslationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/translations")
public class TranslationController {
    private final TranslationService translationService;

    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }
    @GetMapping
    public Page<Translation> getTranslations(@RequestParam(defaultValue = "0") int page_number,
                                             @RequestParam(defaultValue = "10") int page_size) {
        Pageable pageable = PageRequest.of(page_number, page_size);
        return translationService.translationService(pageable);
    }
}
