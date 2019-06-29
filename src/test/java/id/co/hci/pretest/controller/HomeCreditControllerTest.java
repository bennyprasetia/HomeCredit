package id.co.hci.pretest.controller;

import id.co.hci.pretest.dto.ModulesDetailDto;
import id.co.hci.pretest.dto.ModulesDto;
import id.co.hci.pretest.service.HomeCreditService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HomeCreditControllerTest {

    @Mock
    private HomeCreditService service;

    @InjectMocks
    private HomeCreditController homeCreditController = new HomeCreditController();

    private MockMvc mvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(homeCreditController).addPlaceholderValue("/api/user/filter/category/{userId}","/api/user/filter/category/{userId}").build();
    }

    @Test
    public void TestFindUserCategoryFilterWhenModulesDtoNotNull(){
        when(service.getDataByCategoryUserFilter(anyString())).thenReturn(this.createModuleDto());
        final ResponseEntity<ModulesDto> responseEntity = homeCreditController.findUserCategoryFilter("usera");
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
        Assert.assertEquals(responseEntity.getStatusCode().getReasonPhrase(), "OK");
        Assert.assertEquals(responseEntity.getBody().getModules().get(0).getModuleName(), "FlashSale");
        Assert.assertEquals(responseEntity.getBody().getModules().get(0).getModuleOrder(), 1);
        Assert.assertEquals(responseEntity.getBody().getModules().get(1).getModuleName(), "News");
        Assert.assertEquals(responseEntity.getBody().getModules().get(1).getModuleOrder(), 2);
        verify(service, times(1)).getDataByCategoryUserFilter(anyString());
    }

    @Test
    public void TestFindUserCategoryFilterWhenModulesDtoIsNull(){
        when(service.getDataByCategoryUserFilter(anyString())).thenReturn(null);
        final ResponseEntity<ModulesDto> responseEntity = homeCreditController.findUserCategoryFilter("usera");
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
        Assert.assertEquals(responseEntity.getStatusCode().getReasonPhrase(), "Bad Request");
        verify(service, times(1)).getDataByCategoryUserFilter(anyString());
    }

    @Test
    public void TestGetApiFindUserCategoryFilterWhenModulesDtoNotNull()throws Exception
    {
        final String url = "/api/user/filter/category/{userId}";
        when(service.getDataByCategoryUserFilter(anyString())).thenReturn(this.createModuleDto());
        mvc.perform(MockMvcRequestBuilders.get(url,"usera").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
        verify(service, times(1)).getDataByCategoryUserFilter(anyString());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void TestGetApiFindUserCategoryFilterWhenModulesDtoIsNull()throws Exception
    {
        final String url = "/api/user/filter/category/{userId}";
        when(service.getDataByCategoryUserFilter(anyString())).thenReturn(null);
        mvc.perform(MockMvcRequestBuilders.get(url,"usera").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isBadRequest());
        verify(service, times(1)).getDataByCategoryUserFilter(anyString());
        verifyNoMoreInteractions(service);
    }

    public ModulesDto createModuleDto(){
        final ModulesDto modulesDto = new ModulesDto();

        final List<ModulesDetailDto> list = new ArrayList<>();
        ModulesDetailDto modulesDetailDto = new ModulesDetailDto();
        modulesDetailDto.setModuleName("FlashSale");
        modulesDetailDto.setModuleOrder(1);
        list.add(modulesDetailDto);

        modulesDetailDto = new ModulesDetailDto();
        modulesDetailDto.setModuleName("News");
        modulesDetailDto.setModuleOrder(2);
        list.add(modulesDetailDto);

        modulesDto.setModules(list);
        return  modulesDto;
    }
}
