package com.rosendo.transferSystem.controllers.scheduledTransfer;

import com.rosendo.transferSystem.domain.scheduledTransfer.dtos.ScheduledTransferDto;
import com.rosendo.transferSystem.domain.scheduledTransfer.models.ScheduledTransferModel;
import com.rosendo.transferSystem.domain.scheduledTransfer.services.ScheduledTransferServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/internalSystem/scheduledTransactions")
public class ScheduledTransferControllers {

    @Autowired
    ScheduledTransferServices scheduledTransferServices;

    @GetMapping(value = "/{time}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <List<ScheduledTransferModel>> getScheduledTransferByTimeStamp(@PathVariable("time") LocalDateTime time) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduledTransferServices.getScheduledTransfersByTimestamp(time));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <List<ScheduledTransferModel>> getAllScheduledTransfer() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduledTransferServices.findAllScheduledTransfers());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduledTransferModel> createScheduledTransfer(
            @RequestBody @Valid ScheduledTransferDto scheduledTransferDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(scheduledTransferServices.createScheduledTransfer(scheduledTransferDto));
    }
}
