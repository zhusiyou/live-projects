package live.betterman.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhudawei
 * @date: 2024/1/11
 * @description:
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @GetMapping("/")
    public String needPermission(){
        return "need permission!";
    }
}
