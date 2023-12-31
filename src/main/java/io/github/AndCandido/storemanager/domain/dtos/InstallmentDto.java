package io.github.AndCandido.storemanager.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.AndCandido.storemanager.domain.annotations.ValidateInstallmentDto;
import io.github.AndCandido.storemanager.domain.annotations.enums.InstallmentDtoFieldsValidator;
import io.github.AndCandido.storemanager.domain.dtos.groups.RequestGroup;
import io.github.AndCandido.storemanager.domain.dtos.groups.ToPatchGroup;
import io.github.AndCandido.storemanager.domain.enums.PaymentMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@ValidateInstallmentDto.List({
    @ValidateInstallmentDto(
        message = "{validation.installment.HavePaymentMethodWhenIsPaid}",
        fieldsValidator = InstallmentDtoFieldsValidator.HAVE_PAYMENT_METHOD_WHEN_IS_PAID
    )
})
public record InstallmentDto(

    @Null(message = "{field.id.null}", groups = RequestGroup.class)
    UUID id,

    @NotNull(message = "{installment.field.dueDate.null}")
    @Future(message = "{installment.field.dueDate.future}")
    LocalDate dueDate,

    @NotNull(message = "{installment.field.price.null}")
    @Min(value = 0, message = "{installment.field.price.min}")
    Double price,

    @NotNull(message = "{installment.field.paymentMethod.null}", groups = ToPatchGroup.class)
    PaymentMethod paymentMethod,

    @NotNull(message = "{installment.field.isPaid.null}", groups = ToPatchGroup.class)
    Boolean isPaid,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Valid
    CustomerDto customer,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Valid
    SaleDto sale,

    @Null(message = "{field.createdAt.null}", groups = RequestGroup.class)
    LocalDateTime createdAt
) {
}
