package io.github.AndCandido.storemanager.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.AndCandido.storemanager.domain.dtos.groups.RequestGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record CustomerDto(

    @Null(message = "{field.id.null}", groups = RequestGroup.class)
   UUID id,

    @NotBlank(message = "{customer.field.name.blank}")
    @Length(max = 70, message = "{customer.field.name.length}")
    String name,

    String nickname,

    @NotBlank(message = "{customer.field.cpf.blank}")
    @CPF(message = "{customer.field.cpf.invalid}")
    String cpf,

    @NotBlank(message = "{customer.field.address.blank}")
    String address,

    String phone,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Valid
    List<SaleDto> sales,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Valid
    List<InstallmentDto> installments,

    @Null(message = "{field.createdAt.null}", groups = RequestGroup.class)
    LocalDateTime createdAt
) {
}
