package com.entrytest.translationprogram.service.serviceimpl;

import com.entrytest.translationprogram.model.Translation;
import com.entrytest.translationprogram.repository.TranslationRepository;
import com.entrytest.translationprogram.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TranslationServiceImpl implements TranslationService {

    private final TranslationRepository translationRepository;
    @Autowired
    public TranslationServiceImpl(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    @Override
    public Page<Translation> translationService(Pageable pageable) {
        return translationRepository.findAll(pageable);
    }
}
