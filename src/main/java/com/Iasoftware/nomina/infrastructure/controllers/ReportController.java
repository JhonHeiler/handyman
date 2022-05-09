package com.Iasoftware.nomina.infrastructure.controllers;

import com.Iasoftware.nomina.infrastructure.controllers.services.models.ReportDTO;
import com.Iasoftware.nomina.infrastructure.controllers.services.models.ReportInput;
import com.Iasoftware.nomina.infrastructure.controllers.services.ReportServices;
import com.Iasoftware.nomina.shared.domain.WorkingHours;
import com.Iasoftware.nomina.shared.errors.ApplicationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {
    private final ReportServices services;

    public ReportController(ReportServices services) {
        this.services = services;
    }


    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public ResponseEntity<?> createReport(@RequestBody ReportInput reportInput) {
        try {
            ReportDTO reportService = services.createService(reportInput);
            return ResponseEntity.ok(reportService);
        } catch (IllegalArgumentException | NullPointerException e) {
            ApplicationError error = new ApplicationError("InputDataValidationError",
                    "Bad input data", Map.of("error", e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (Exception e) {
            ApplicationError error = new ApplicationError("SystemError", e.getMessage(), Map.of());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }


    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ResponseEntity<WorkingHours> list(
            @RequestParam(name = "idTechnical") String idTechnical,
            @RequestParam(name = "weekNumber") Integer weekNumber
    ) {
        try {
            return ResponseEntity.ok(services.queryServices(idTechnical, weekNumber));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
