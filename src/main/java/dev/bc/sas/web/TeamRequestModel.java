package dev.bc.sas.web;

import dev.bc.sas.validation.TeamId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

record TeamRequestModel(@TeamId(nullable = true) Long teamId, @NotBlank @Size(min = 1, max = 255) String name) {

}
