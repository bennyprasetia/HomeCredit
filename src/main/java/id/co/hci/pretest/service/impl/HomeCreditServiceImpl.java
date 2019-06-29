package id.co.hci.pretest.service.impl;

import id.co.hci.pretest.dto.ModulesDto;
import id.co.hci.pretest.entity.UserCategoryFilter;
import id.co.hci.pretest.factory.HomeCreditFactory;
import id.co.hci.pretest.repository.UserCategoryFilterRepository;
import id.co.hci.pretest.service.HomeCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeCreditServiceImpl implements HomeCreditService {

    @Autowired
    private UserCategoryFilterRepository repository;

    @Autowired
    private HomeCreditFactory factory;

    @Override
    public ModulesDto getDataByCategoryUserFilter(final String userId) {
        final List<UserCategoryFilter> userCategoryFilterList = repository.findByUserId(userId);
        final ModulesDto modulesDto = factory.createModules(userCategoryFilterList);
        return modulesDto;
    }

}
