package id.co.hci.pretest.dto;

import java.util.List;

public class ModulesDto {

    private List<ModulesDetailDto> modules;

    public List<ModulesDetailDto> getModules() {
        return modules;
    }

    public void setModules(List<ModulesDetailDto> modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return "ModulesDto{" +
                "modules=" + modules +
                '}';
    }
}
