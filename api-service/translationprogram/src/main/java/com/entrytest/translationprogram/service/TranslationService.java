package com.entrytest.translationprogram.service;

import com.entrytest.translationprogram.model.Translation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TranslationService {
    Page<Translation> translationService(Pageable pageable);
}
