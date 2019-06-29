package id.co.hci.pretest.factory;

import id.co.hci.pretest.dto.ModulesDto;
import id.co.hci.pretest.entity.Category;
import id.co.hci.pretest.entity.User;
import id.co.hci.pretest.entity.UserCategoryFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeCreditFactoryTest {

    private HomeCreditFactory factory = new HomeCreditFactory();

    @Test
    public void TestCreateModulesWhenListIsEmpty() {
        final List<UserCategoryFilter> list = new ArrayList<>();
        final ModulesDto modulesDto = factory.createModules(list);
        Assert.assertNull(modulesDto);
    }

    @Test
    public void TestCreateModulesWhenListNotNull() {
        final List<UserCategoryFilter> list = this.createList();
        final ModulesDto modulesDto = factory.createModules(list);
        Assert.assertNotNull(modulesDto);
        Assert.assertEquals(modulesDto.getModules().get(0).getModuleName(), "FlashSale");
        Assert.assertEquals(modulesDto.getModules().get(0).getModuleOrder(), 1);
    }

    public List<UserCategoryFilter> createList() {
        final List<UserCategoryFilter> list = new ArrayList<>();

        final Category category = new Category();
        category.setCreatedDate(new Date());
        category.setUpdatedDate(new Date());
        category.setName("FlashSale");

        final User user = new User();
        user.setName("usera");

        final UserCategoryFilter filter = new UserCategoryFilter();
        filter.setCategory(category);
        filter.setUser(user);
        filter.setOrderId(1);
        filter.setCreatedDate(new Date());
        filter.setUpdatedDate(new Date());

        list.add(filter);
        return list;
    }
}
