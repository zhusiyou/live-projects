package live.betterman.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhudawei
 * @date: 2023/12/27
 * @description:
 */
@Api(tags = "测试接口")
@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperation("测试get接口")
    @GetMapping("/")
    public String getString(){
        return "to be a better man!";
    }
}
