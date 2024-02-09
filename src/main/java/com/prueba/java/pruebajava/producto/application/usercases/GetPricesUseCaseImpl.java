package com.prueba.java.pruebajava.producto.application.usercases;

import com.prueba.java.pruebajava.producto.domain.model.PriceDomain;
import com.prueba.java.pruebajava.producto.domain.ports.in.GetPricesUseCase;
import com.prueba.java.pruebajava.producto.domain.ports.out.PriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GetPricesUseCaseImpl implements GetPricesUseCase {
    @Autowired
    PriceRepository priceRepository;
    private static final Logger logger = LoggerFactory.getLogger(GetPricesUseCaseImpl.class);

    @Override
    public PriceDomain getPricesByBrandIdAndProductIdAndDate(Integer brandId, Long productId, LocalDateTime dateTime) {
        validateParameters(brandId, productId, dateTime);

        return priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrder(
                        brandId, productId, dateTime)
                .orElseThrow(() -> new NoSuchElementException("No se encontró ningún precio para la marca, producto y fecha proporcionados"));
    }

    /**
     * Devuelve la lista de precios para una marca, producto y fecha específicos.
     * Esta implementación se utiliza como una solución opcional en caso de que la lógica
     * de la base de datos no cumpla con los criterios acordados. Si se encuentran varios precios
     * con la misma prioridad, se emite un log de advertencia y se devuelven los precios ordenados
     * por precio de mayor a menor.
     *
     * @param brandId   ID de la marca.
     * @param productId ID del producto.
     * @param date      Fecha para la cual se buscan los precios.
     * @return Lista de precios para la marca, producto y fecha especificados.
     * @throws NoSuchElementException si no se encuentra ningún precio para la marca, producto y fecha proporcionados.
     */
    @Override
    public List<PriceDomain> getPriceListsByBrandIdAndProductIdAndDate(Integer brandId, Long productId, LocalDateTime date) {
        List<PriceDomain> prices = priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderList(brandId, productId, date);
        if (prices.isEmpty()) {
            throw new NoSuchElementException("No se encontró ningún precio para la marca, producto y fecha proporcionados");
        }
        // Ordenar los precios por prioridad de forma descendente
        prices.sort(Comparator.comparingInt(PriceDomain::getPriority).reversed());

        // Obtener el precio con la prioridad más alta
        int highestPriority = prices.get(0).getPriority();
        List<PriceDomain> highestPriorityPrices = prices.stream()
                .filter(price -> price.getPriority() == highestPriority)
                .collect(Collectors.toList());

        if (highestPriorityPrices.size() > 1) {
            // Log de alerta indicando que se ha incumplido la lógica de negocio
            logger.warn("Se encontraron múltiples precios con la misma prioridad más alta");

            // Devolver los precios ordenados por precio de mayor a menor
            highestPriorityPrices.sort(Comparator.comparing(PriceDomain::getPrice).reversed());
        }

        return highestPriorityPrices;


    }

    private void validateParameters(Integer brandId, Long productId, LocalDateTime dateTime) {
        if (brandId == null || productId == null || dateTime == null) {
            throw new IllegalArgumentException("Los parámetros no pueden ser nulos");
        }
    }
}
