package id.co.hci.pretest.dto;

import id.co.hci.pretest.tester.EntityTester;

public class ModulesDtoTest extends EntityTester<ModulesDto> {

    public ModulesDtoTest(){
        super(null, null);
    }

    @Override
    protected ModulesDto getInstance() {
        return new ModulesDto();
    }
}
