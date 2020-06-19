package com.openelective.project.controller;

import com.openelective.project.service.AllocationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/allocate")
public class AllocationController {

    private AllocationService allocationService;

    public AllocationController(AllocationService allocationService){
        this.allocationService = allocationService;
    }

    @GetMapping(value="/differential-allocation")
    @ResponseStatus(HttpStatus.OK)
    public void differentialAllocation() {
        allocationService.differentialAllocation();
    }

    @GetMapping(value = "/combined-allocation")
    @ResponseStatus(HttpStatus.OK)
    public void combinedAllocation() {
        allocationService.combinedAllocation();
    }

    @GetMapping(value="/completeAllocation")
    @ResponseStatus(HttpStatus.OK)
    public void completeAllocation(){
        allocationService.completeAllocationForSemester();
    }
}
