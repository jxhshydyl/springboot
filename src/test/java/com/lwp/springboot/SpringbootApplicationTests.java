package com.lwp.springboot;

import com.lwp.springboot.dao.TestDao;
import com.lwp.springboot.dto.User;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {
    @Autowired
    TestDao testDao;

    private MockMvc mockMvc; //模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。

    @Autowired
    private WebApplicationContext wac; //注入WebApplicationContext

    @Before
    public void setup() {
        //MockMvcBuilders使用构建MockMvc对象   （项目拦截器有效）
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        //this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        //单个类  拦截器无效
        // mockMvc = MockMvcBuilders.standaloneSteup(userController).build();
    }

    @Test
    public void contextLoads() {
        User user = new User();
        user.setName("lwp");
        user.setPassword("12385");
        int insert = testDao.insert(user);
        System.out.println(insert);
    }


    @Test
    public void testInfo() throws Exception {
        JSONObject param = new JSONObject();
        param.put("userId", "");
        String jsonstr = param.toString();
        System.out.println("================================请求入参：" + jsonstr);
        RequestBuilder request = MockMvcRequestBuilders.post("/test/getString")
                .contentType(MediaType.ALL)
                .header("SESSIONNO", "")
                .content(jsonstr);

        MvcResult mvcResult = mockMvc.perform(request).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        Assert.assertTrue("正确", status == 200);
        Assert.assertFalse("错误", status != 200);

        System.out.println("返回结果：" + status);
        System.out.println(content);
    }

    /*	@Test
        public void testQ1() throws Exception {
            Map<String, Object> map = new HashMap<>();
            map.put("address", "合肥");

            MvcResult result = mockMvc.perform(post("/q1?address=合肥").content(JSONObject.toJSONString(map)))
                    .andExpect(status().isOk())// 模拟向testRest发送get请求
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                    .andReturn();// 返回执行请求的结果

            System.out.println(result.getResponse().getContentAsString());
        }*/
    @Test
    public void contextLoad() throws Exception {
        RequestBuilder request = get("/test");
        mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().string("hello world"));

        request = get("/test/getString").param("name", "无境");
        ResultActions resultActions = mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().string("{\"name\":\"无境\",\"title\":\"hello world\"}"));
        System.out.println(resultActions);
    }
}
