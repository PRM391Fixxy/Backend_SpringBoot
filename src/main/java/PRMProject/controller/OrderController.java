package PRMProject.controller;


import PRMProject.entity.Order;
import PRMProject.model.RequestOrderDTO;
import PRMProject.model.ResponseDTO;
import PRMProject.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAll(@RequestParam(required = false) String workerName) {
        try {
            log.info("getAll");
            List<Order> order = orderService.getAll(workerName);
            return ResponseEntity.ok(order);
        } finally {
            log.info("getAll");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        try {
            log.info("getById");
            Order order = orderService.getById(id);
            return ResponseEntity.ok(order);
        } finally {
            log.info("getById");
        }
    }

    @PostMapping
    public ResponseEntity<Order> requestOrder(@RequestBody RequestOrderDTO order) {
        try {
            log.info("requestOrder");
            Order rs = orderService.requestOrder(order);
            return ResponseEntity.ok(rs);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("requestOrder");
        }
        return null;
    }

    @PutMapping(value = "/{orderID}")
    public ResponseEntity<ResponseDTO> acceptOrder(@PathVariable Long orderID) {
        try {
            log.info("requestOrder");
            Order rs = orderService.acceptOrder(orderID);
            return ResponseEntity.ok(
                    ResponseDTO.builder()
                            .message("success")
                            .status(200)
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body( ResponseDTO.builder()
                    .message("this order is not avaiable")
                    .status(400)
                    .build());
        } finally {
            log.info("requestOrder");
        }
    }

}
