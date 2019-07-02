package com.zys.day9;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        saveMessages();
    }

    /**
     * 新增消息，没有id，使用AtomicLong的自增id
     * @throws Exception
     */
    @Test
    public void saveMessage() throws Exception {
        //用来存储需要发送的请求参数
        final MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("text","text");
        params.add("summary","summary");
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/message")
                .params(params)).andReturn().getResponse().getContentAsString();
        //id自增为10，输出新增的消息
        System.out.println("Result:"+result);
    }

    //查询所有的消息
    @Test
    public void getAllMessages() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/messages")).andReturn().getResponse().getContentAsString();
        System.out.println("Result:"+result);
    }

    //查看id为6的消息
    @Test
    public void getMessage() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/message/6")).andReturn().getResponse().getContentAsString();
        System.out.println("Result:"+result);
    }

    //put请求修改整条消息
    @Test
    public void modifyMessage() throws Exception {
        final MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("id","6");
        params.add("text","text");
        params.add("summary","summary");
        String result = mockMvc.perform(MockMvcRequestBuilders.put("/message")
                .params(params)).andReturn().getResponse().getContentAsString();
        System.out.println("Result:"+result);
    }

    //Patch请求修改请求的text字段
    @Test
    public void patchMessage() throws Exception {
        final MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("id","6");
        params.add("text","textChange");
        String result = mockMvc.perform(MockMvcRequestBuilders.patch("/message/text")
                .params(params)).andReturn().getResponse().getContentAsString();
        System.out.println("Result:"+result);
    }

    //删除id为6的商品
    @Test
    public void deleteMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/message/6")).andReturn();
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/messages")).andReturn().getResponse().getContentAsString();
        System.out.println("Result:"+result);
    }

    private void saveMessages(){
        for (int i=1;i<10;i++){
            final MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
            params.add("text","text"+i);
            params.add("summary","summary"+i);
            try {
                MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/message").
                        params(params)).andReturn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
