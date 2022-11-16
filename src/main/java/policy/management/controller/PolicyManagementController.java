package policy.management.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import policy.management.dto.*;
import policy.management.service.PolicyManagementService;

@RestController
public class PolicyManagementController {

    private final PolicyManagementService policyManagementService;


    @Autowired
    public PolicyManagementController(PolicyManagementService policyManagementService) {
        this.policyManagementService = policyManagementService;
    }

    @PostMapping("policies")
    public ResponseEntity<CreatePolicyResponseDTO> create(@RequestBody CreatePolicyDTO createPolicyDTO) {
        return new ResponseEntity<>(policyManagementService.createPolicy(createPolicyDTO), HttpStatus.OK);
    }

    @PutMapping("policies")
    public ResponseEntity<UpdatePolicyResponseDTO> update(@RequestBody UpdatePolicyDTO updatePolicyDTO) {
        return new ResponseEntity<>(policyManagementService.updatePolicy(updatePolicyDTO), HttpStatus.OK);
    }

    @GetMapping("policies/id/{id}")
    public ResponseEntity<GetPolicyResponseDTO> get(@PathVariable("id") Long policyId, @RequestParam(required = false) String requestDate) {
        return new ResponseEntity<>(policyManagementService.getPolicyByIdAndDate(policyId, requestDate), HttpStatus.OK);
    }
}
