package pe.bideafactory.api.booking.thrirdparty.internal.discount;


import feign.RetryableException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pe.bideafactory.api.booking.thrirdparty.internal.discount.dto.DiscountDtoRequest;
import pe.bideafactory.api.booking.thrirdparty.internal.discount.dto.DiscountDtoResponse;

import java.util.concurrent.TimeoutException;

@FeignClient(name = "discountApi", url = "${api.booking.discountApi.resourcePath}")
public interface DiscountApiClient {

  @Retryable(retryFor = RetryableException.class, maxAttempts = 3, backoff = @Backoff(delay = 100))
  @PostMapping("/address/{id}")
  ResponseEntity<DiscountDtoResponse> retrieveDiscount(@RequestBody DiscountDtoRequest discountRequest);

}

