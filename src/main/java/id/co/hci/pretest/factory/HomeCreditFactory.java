package id.co.hci.pretest.factory;

import id.co.hci.pretest.dto.ModulesDetailDto;
import id.co.hci.pretest.dto.ModulesDto;
import id.co.hci.pretest.entity.UserCategoryFilter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HomeCreditFactory {

    public ModulesDto createModules(final List<UserCategoryFilter> list) {
        ModulesDto modulesDto = null;
        if (!list.isEmpty()) {
            modulesDto = new ModulesDto();
            final List<ModulesDetailDto> detailDtoList = new ArrayList<>();
            for (UserCategoryFilter data : list) {
                final ModulesDetailDto detailDto = new ModulesDetailDto();
                detailDto.setModuleName(data.getCategory().getName());
                detailDto.setModuleOrder(data.getOrderId());
                detailDtoList.add(detailDto);
            }
            modulesDto.setModules(detailDtoList);
        }
        return modulesDto;
    }

}
