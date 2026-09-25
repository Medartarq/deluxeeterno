package com.deluxeterno.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryForm {

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 80, message = "El nombre no debe superar 80 caracteres.")
    private String name;

    @Size(max = 255, message = "La descripción no debe superar 255 caracteres.")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
