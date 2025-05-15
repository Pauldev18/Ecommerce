package com.ecommerce.Ecomerce.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.ecommerce.Ecomerce.Entity.Attribute;
import com.ecommerce.Ecomerce.Service.AttributeService;
import com.ecommerce.Ecomerce.controller.AttributeController;
import com.ecommerce.Ecomerce.controller.CustomUtils;
import com.ecommerce.Ecomerce.dto.AttributeDto;
import com.ecommerce.Ecomerce.mapper.AttributeMapper;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@Transactional
public class AttributeControllerTest {
    private static final String ENDPOINT_URL = "/api/attribute";

    @InjectMocks
    private AttributeController attributeController;

    @Mock
    private AttributeService attributeService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(attributeController)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<AttributeDto> page = new PageImpl<>(Collections.singletonList(AttributeBuilder.getDto()));

        Mockito.when(attributeService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(attributeService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(attributeService);
    }

    @Test
    public void getById() throws Exception {
        Mockito.when(attributeService.findById(ArgumentMatchers.anyUUID()))
                .thenReturn(AttributeBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

        Mockito.verify(attributeService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(attributeService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(attributeService.save(ArgumentMatchers.any(AttributeDto.class)))
                .thenReturn(AttributeBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(AttributeBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(attributeService, Mockito.times(1)).save(ArgumentMatchers.any(AttributeDto.class));
        Mockito.verifyNoMoreInteractions(attributeService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(attributeService.update(ArgumentMatchers.any(), ArgumentMatchers.anyUUID()))
                .thenReturn(AttributeBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(AttributeBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(attributeService, Mockito.times(1))
                .update(ArgumentMatchers.any(AttributeDto.class), ArgumentMatchers.anyUUID());
        Mockito.verifyNoMoreInteractions(attributeService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(attributeService).deleteById(ArgumentMatchers.anyUUID());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(AttributeBuilder.getIds())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(attributeService, Mockito.times(1))
                .deleteById(Mockito.anyUUID());
        Mockito.verifyNoMoreInteractions(attributeService);
    }
}
