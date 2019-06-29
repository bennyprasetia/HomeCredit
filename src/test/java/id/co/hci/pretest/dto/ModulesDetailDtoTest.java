package id.co.hci.pretest.dto;

import id.co.hci.pretest.tester.EntityTester;

public class ModulesDetailDtoTest extends EntityTester<ModulesDetailDto> {

    public ModulesDetailDtoTest(){
        super(null,null);
    }

    @Override
    protected ModulesDetailDto getInstance() {
        return new ModulesDetailDto();
    }
}
