package live.betterman;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: zhudawei
 * @date: 2023/12/27
 * @description:
 */
@SpringBootApplication
@MapperScan("live.betterman.**.dao")
public class ApiApplication {
    public static void main(String[] args){
        SpringApplication.run(ApiApplication.class);
    }
}
