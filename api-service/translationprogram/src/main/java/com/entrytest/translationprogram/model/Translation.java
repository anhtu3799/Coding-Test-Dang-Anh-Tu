package com.entrytest.translationprogram.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "translation")
public class Translation{
    @Id
    private Long id;
    private String text;
    private String audioUrl;
    private Long translateId;
    private String translateText;
}
