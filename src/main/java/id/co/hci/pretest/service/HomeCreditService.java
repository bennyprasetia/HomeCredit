package id.co.hci.pretest.service;

import id.co.hci.pretest.dto.ModulesDto;

public interface HomeCreditService {

    ModulesDto getDataByCategoryUserFilter(String userId);

}
