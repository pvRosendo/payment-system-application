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

import java.util.UUID;

@RestController
@RequestMapping(value = "/internalSystem/scheduledTransactions")
public class ScheduledTransferControllers {

    @Autowired
    ScheduledTransferServices scheduledTransferServices;


    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduledTransferModel> getScheduledTransferById(
            @PathVariable("id") UUID idScheduledTransfer)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(scheduledTransferServices.getScheduledTransferById(idScheduledTransfer));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduledTransferModel> createScheduledTransfer(
            @RequestBody @Valid ScheduledTransferDto scheduledTransferDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(scheduledTransferServices.createScheduledTransfer(scheduledTransferDto));
    }
}
