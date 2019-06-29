package id.co.hci.pretest.dto;

public class ModulesDetailDto {

    private String moduleName;

    private int moduleOrder;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getModuleOrder() {
        return moduleOrder;
    }

    public void setModuleOrder(int moduleOrder) {
        this.moduleOrder = moduleOrder;
    }

    @Override
    public String toString() {
        return "ModulesDetailDto{" +
                "moduleName='" + moduleName + '\'' +
                ", moduleOrder=" + moduleOrder +
                '}';
    }
}
