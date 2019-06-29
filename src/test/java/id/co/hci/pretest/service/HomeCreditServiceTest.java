package id.co.hci.pretest.service;

import id.co.hci.pretest.dto.ModulesDto;
import id.co.hci.pretest.entity.Category;
import id.co.hci.pretest.entity.User;
import id.co.hci.pretest.entity.UserCategoryFilter;
import id.co.hci.pretest.factory.HomeCreditFactory;
import id.co.hci.pretest.repository.UserCategoryFilterRepository;
import id.co.hci.pretest.service.impl.HomeCreditServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HomeCreditServiceTest {

    @Mock
    private UserCategoryFilterRepository repository;

    @Spy
    private HomeCreditFactory factory;

    @InjectMocks
    private HomeCreditService service = new HomeCreditServiceImpl();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void TestGetDataByCategoryUserFilterWhenListNotNull(){
        when(repository.findByUserId(anyString())).thenReturn(this.createList());
        final ModulesDto modulesDto = service.getDataByCategoryUserFilter("usera");
        Assert.assertNotNull(modulesDto);
        Assert.assertEquals(modulesDto.getModules().get(0).getModuleName(), "FlashSale");
        Assert.assertEquals(modulesDto.getModules().get(0).getModuleOrder(), 1);
        verify(repository, times(1)).findByUserId(any());
    }

    @Test
    public void TestGetDataByCategoryUserFilterWhenListIsEmpty(){
        when(repository.findByUserId(anyString())).thenReturn(new ArrayList<>());
        final ModulesDto modulesDto = service.getDataByCategoryUserFilter("usera");
        Assert.assertNull(modulesDto);
        verify(repository, times(1)).findByUserId(any());
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
