package tacos.web;

import org.springframework.data.repository.CrudRepository;
import tacos.TacoOrder;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> findByDeliveryCityOrderByPlacedAtDesc(String deliveryCity);

    List<TacoOrder> findByDeliveryZipAndPlacedAtBetween(String deliveryZip,
                                                        java.util.Date startDate,
                                                        java.util.Date endDate);

    /*
    @Query("Order o where o.deliveryCity = 'Seattle' and o.deliveryState = 'WA'")
    List<TacoOrder> readDeliverdInSeattle();
     */
}
