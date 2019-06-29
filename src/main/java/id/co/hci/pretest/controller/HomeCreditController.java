package id.co.hci.pretest.controller;

import id.co.hci.pretest.dto.ModulesDto;
import id.co.hci.pretest.service.HomeCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class HomeCreditController {

    @Autowired
    private HomeCreditService service;

    @GetMapping(value = "/api/user/filter/category/{userId}", produces = "application/json")
    public ResponseEntity<ModulesDto> findUserCategoryFilter(@PathVariable("userId") String userId) {
        final ModulesDto modulesDto = service.getDataByCategoryUserFilter(userId);
        if (null == modulesDto) {
            return new ResponseEntity<>(modulesDto, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modulesDto, HttpStatus.OK);
    }

}
