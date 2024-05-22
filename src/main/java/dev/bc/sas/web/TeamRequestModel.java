package dev.bc.sas.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

record TeamRequestModel(@NotBlank @Size(min = 1, max = 255) String name) {

}
